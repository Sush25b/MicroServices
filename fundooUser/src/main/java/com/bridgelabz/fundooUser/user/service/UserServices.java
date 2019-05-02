package com.bridgelabz.fundooUser.user.service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundooUser.user.dto.LoginDto;
import com.bridgelabz.fundooUser.user.dto.UserDto;
import com.bridgelabz.fundooUser.user.response.UserResp;
import com.bridgelabz.fundooUser.user.response.UserResponse;



public interface UserServices 
{
	//, HttpServletResponse  response
	ResponseEntity<UserResp> onLogin(LoginDto loginDto) ;
	ResponseEntity<UserResponse> onRegister(UserDto userDto) ;
	ResponseEntity<UserResponse> forgetPassword(UserDto userDto) ;
	ResponseEntity<UserResponse> resetPassword(String token, String password) ;
	ResponseEntity<UserResponse> validEmail(String token) ;
	void sendmail(String mailSubject, Long userId, String  s);	
	ResponseEntity<UserResponse> setProfilePic(String token, MultipartFile picture);
}




