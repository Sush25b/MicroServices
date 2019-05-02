package com.bridgelabz.fundooUser.user.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooUser.user.dto.UserDto;
import com.bridgelabz.fundooUser.user.exception.UserException;
import com.bridgelabz.fundooUser.user.model.User;
import com.bridgelabz.fundooUser.user.repository.IUserRepository;


//import com.bridgelabz.fundooNotes.user.dto.UserDto;


//import com.bridgelabz.fundooNotes.service.User;
//import com.bridgelabz.fundooNotes.service.UserRepository;

@Service
public class UserServiceImpl implements UserService
{
	
	@Value("${sender.email}")
	String from;
	
	@Value("${sender.password}")
	String password;
	
	String to = null;
	//String receiver= null;
	String msgheader= "Welcome to Bridgeit ";
	String textmsg="Your account has been added successfully";
	
	@Autowired
	private IUserRepository userrepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
//	
	/*
	 * @Autowired public UserServiceImpl(IUserRepository userrepository) {
	 * userrepository = userrepository; }
	 */
		
	Date date=new Date();
	
	String registerDate,updateDate= date.toString();
	
	@Override
	public List<User> findAll()
	{
		return userrepository.findAll();
	}

	@Override
	public User findById(Long id) {
		User user=new User();
		
		Optional<User> result = userrepository.findById(id);
		
		if (result.isPresent())
		{
			user = result.get();
		}
		else 
		{
			throw new UserException("*****User not found plz put valid id no");
		}
	
		return user;
	}

	@Override
	public void save(UserDto userDTO) 
	{
		User user= modelMapper.map(userDTO, User.class);
		
		user.setUpdateDate(updateDate);
		
		user.setRegisterDate(registerDate);
		
		user.setIsVerified("false");  
		
		userrepository.save(user);
		
		//from,password,to,subject,message  
	     //MailHelper.send(from,password,"phulsundarsushant26@gmail.com",msgheader,textmsg); 
		
	}

	@Override
	public void deleteById(Long id)
	{
		userrepository.deleteById(id);
	}

}











