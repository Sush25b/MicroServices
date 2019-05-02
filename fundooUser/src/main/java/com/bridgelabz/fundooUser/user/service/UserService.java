package com.bridgelabz.fundooUser.user.service;

import java.util.List;

import com.bridgelabz.fundooUser.user.dto.UserDto;
import com.bridgelabz.fundooUser.user.model.User;

public interface UserService 
{
	 List<User> findAll();
	
	 User findById(Long theId);
	
	 void save(UserDto userDTO);
	
	 void deleteById(Long theId);
}

