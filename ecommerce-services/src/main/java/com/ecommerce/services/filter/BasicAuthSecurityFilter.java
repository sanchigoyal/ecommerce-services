package com.ecommerce.services.filter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.services.bean.ErrorMessage;
import com.ecommerce.services.bean.User;
import com.ecommerce.services.bean.UserRole;
import com.ecommerce.services.service.UserService;

@Provider
@Transactional
public class BasicAuthSecurityFilter implements ContainerRequestFilter{

	private static final String VERIFY_USER_ROLE_MESSAGE = "verify user role";

	private static final String NO_ROLES_ARE_DEFINED_FOR_THIS_RESOURCE_MESSAGE = "no roles are defined for this resource";

	private static final String HTTP_403_FORBIDDEN = "403";

	private static final String THIS_IS_A_FORBIDDEN_RESOURCE_MESSAGE = "this is a forbidden resource";

	private static final String ACCESS_TO_THIS_RESOURCE_IS_NOT_ALLOWED_MESSAGE = "access to this resource is not allowed";

	private static final String VERIFY_USERNAME_AND_PASSWORD_MESSAGE = "verify username and password";

	private static final String HTTP_401_UNAUTHORIZED = "401";

	private static final String VERIFY_AUTHORIZATION_HEADER_VALUE_MESSAGE = "verify authorization header property";

	private static final String INCORRECT_USERNAME_AND_PASSWORD_MESSAGE = "incorrect username and password";

	@Context 
	ResourceInfo resourceInfo;
	@Autowired
	UserService userService;
	
	private static final String AUTHORIZATION_HEADER_PROPERTY = "Authorization";
	private static final String AUTHENTICATION_SCHEME = "Basic ";
	
	@Override
	public void filter(ContainerRequestContext context) throws IOException {
		Class<?> resourceClass = resourceInfo.getResourceClass();
		Method resourceMethod = resourceInfo.getResourceMethod();
		
		// access not allowed for all
		if(!resourceClass.isAnnotationPresent(PermitAll.class))
		{
			/*
			 * if denied at class level look at method level.
			 * @DenyAll should be kept on all resource class
			 * to avoid any open method when a new one is added.
			 */
			if(resourceClass.isAnnotationPresent(DenyAll.class))
			{
				// access not allowed for all on method level
				if(!resourceMethod.isAnnotationPresent(PermitAll.class))
				{
					// fetch authorization header
		            List<String> authorization = getAuthorizationHeader(context);
		            
		            // if no authorization information present; block access
		            if(authorization == null || authorization.isEmpty())
		            {
		                ErrorMessage error = new ErrorMessage(
		                		1002, 
		                		INCORRECT_USERNAME_AND_PASSWORD_MESSAGE, 
		                		VERIFY_AUTHORIZATION_HEADER_VALUE_MESSAGE, 
		                		HTTP_401_UNAUTHORIZED);
		                
		                context.abortWith(Response.status(Status.UNAUTHORIZED)
		                						.type(MediaType.APPLICATION_JSON)
		                						.entity(error)
		                						.build());
		                return;
		            }
		            
		            // get encoded username and password
		            final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME, "");
		            
		            // decode username and password
		            String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));
		            
		            // split username and password tokens
		            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		            final String username = tokenizer.nextToken();
		            final String password = tokenizer.nextToken();
		            
		            System.out.println("Sanchi>>Debug>>username -" +username+", password - "+ password);
		            
		            // verify username and password are correct
		            // TODO - Do password encryption before matching
		            User user = userService.findOneUserByEmailAndPassword(username, password);
		            if(user == null)
		            {
		            	ErrorMessage error = new ErrorMessage(
		                		1002, 
		                		INCORRECT_USERNAME_AND_PASSWORD_MESSAGE, 
		                		VERIFY_USERNAME_AND_PASSWORD_MESSAGE, 
		                		HTTP_401_UNAUTHORIZED);
		                
		                context.abortWith(Response.status(Status.UNAUTHORIZED)
		                						.type(MediaType.APPLICATION_JSON)
		                						.entity(error)
		                						.build());
		                return;
		            }
		            
					// access denied for all 
					if(resourceMethod.isAnnotationPresent(DenyAll.class))
					{
						ErrorMessage error = new ErrorMessage(
								1002, 
								ACCESS_TO_THIS_RESOURCE_IS_NOT_ALLOWED_MESSAGE, 
								THIS_IS_A_FORBIDDEN_RESOURCE_MESSAGE, 
								HTTP_403_FORBIDDEN);
						
						context.abortWith(Response.status(Status.FORBIDDEN)
        						.entity(error)
        						.build());
						return;
					}
					
		            // verify user access if anything else
					RolesAllowed rolesAnnotation = resourceMethod.getAnnotation(RolesAllowed.class);
		                
	                /*
	                 *  if no roles are set, block the access to 
	                 *  this resource. Developer should always 
	                 *  put a role on each method resource.
	                 */
	                if(rolesAnnotation == null)
	                {
	                	ErrorMessage error = new ErrorMessage(
								1002, 
								ACCESS_TO_THIS_RESOURCE_IS_NOT_ALLOWED_MESSAGE, 
								NO_ROLES_ARE_DEFINED_FOR_THIS_RESOURCE_MESSAGE, 
								HTTP_403_FORBIDDEN);
						
						context.abortWith(Response.status(Status.FORBIDDEN)
								.type(MediaType.APPLICATION_JSON)
        						.entity(error)
        						.build());
						return;
	                }
	                
	                // finally check if the user has a valid role to access the resource
	                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
	                if( ! isUserAllowed(user, rolesSet))
	                {
	                	ErrorMessage error = new ErrorMessage(
								1002, 
								ACCESS_TO_THIS_RESOURCE_IS_NOT_ALLOWED_MESSAGE, 
								VERIFY_USER_ROLE_MESSAGE, 
								HTTP_403_FORBIDDEN);
						
						context.abortWith(Response.status(Status.FORBIDDEN)
								.type(MediaType.APPLICATION_JSON)
        						.entity(error)
        						.build());
						return;
	                }
	                else
	                {
	                	/*
	                	 * add user id to the header so that
	                	 * it can be accessed in the methods
	                	 * to bind resource content based on 
	                	 * authenticated resource.
	                	 * i.e. if john's credential is passed,
	                	 * even though he has role as MEMBER,
	                	 * he should be able to access only his
	                	 * content.
	                	 */
	                	context.getHeaders().putSingle("USER-ID", String.valueOf(user.getId()));
	                }
				}
			}
		}
	}
	
	private boolean isUserAllowed(User user, Set<String> rolesSet) {
		
		boolean isUserAllowed = false;
		List<UserRole> roles = user.getRoles();
		
		for(UserRole userRole : roles)
		{
			if(rolesSet.contains(userRole.getRole()))
			{
				isUserAllowed = true;
				break;
			}
		}
		
		return isUserAllowed;
	}

	private List<String> getAuthorizationHeader(ContainerRequestContext requestContext)
	{
		// get request headers
        final MultivaluedMap<String, String> headers = requestContext.getHeaders();
        
        // fetch authorization header
        final List<String> authorization = headers.get(AUTHORIZATION_HEADER_PROPERTY);
        
        return authorization;
	}
}
