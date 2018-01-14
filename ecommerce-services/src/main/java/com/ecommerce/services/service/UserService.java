package com.ecommerce.services.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.services.bean.User;
import com.ecommerce.services.entity.UserEntity;
import com.ecommerce.services.exception.RecordNotFoundException;
import com.ecommerce.services.repository.UserRepository;
import com.ecommerce.services.util.LinkGenerator;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRespository;

	public User getUser(int userId, UriInfo uriInfo, boolean expand) {
	
		UserEntity entity = userRespository.findOne(userId);
		if(entity == null)
		{
			throw new RecordNotFoundException(userId, "user record not found");
		}
		
		User user = new User();
		if(expand)
		{
			user.copyProperties(entity);
		}
		else
		{
			user.copyPropertiesLazyFetch(entity);
		}
		
		addLinks(uriInfo, user);
		return user;
	}
	
	public List<User> getAllUsers(UriInfo uriInfo, boolean expand)
	{
		Iterable<UserEntity> entities = userRespository.findAll();
		List<User> users = new ArrayList<User>();
		
		if(expand)
		{
			entities.forEach(item->{
				User user = new User();
				user.copyProperties(item);
				addLinks(uriInfo, user);
				users.add(user);
			});
		}
		else
		{
			entities.forEach(item->{
				User user = new User();
				user.copyPropertiesLazyFetch(item);
				addLinks(uriInfo, user);
				users.add(user);
			});
		}
		
		return users;
	}
	
	public void addLinks(UriInfo uriInfo, User user)
	{
		user.addLink(
				"self",
				LinkGenerator.getUserResourceInstanceURL(uriInfo, user.getId()));
	}

	public User findOneUserByEmailAndPassword(String username, String password) {
		
		UserEntity entity = userRespository.findOneUserByEmailAndPassword(username, password);
		if(entity == null)
		{
			return null;
		}
		
		User user = new User();
		user.copyProperties(entity);
		return user;
	}
}
