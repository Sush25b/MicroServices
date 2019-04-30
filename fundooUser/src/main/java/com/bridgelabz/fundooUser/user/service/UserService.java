package com.bridgelabz.fundooNotes.user.service;

import java.util.List;

import com.bridgelabz.fundooNotes.user.dto.UserDto;
import com.bridgelabz.fundooNotes.user.model.User;

public interface UserService 
{
	 List<User> findAll();
	
	 User findById(Long theId);
	
	 void save(UserDto userDTO);
	
	 void deleteById(Long theId);
}

