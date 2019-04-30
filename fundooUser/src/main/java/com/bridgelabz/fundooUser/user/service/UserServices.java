package com.bridgelabz.fundooNotes.user.service;

import java.io.UnsupportedEncodingException;

import javax.mail.Multipart;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundooNotes.user.dto.LoginDto;
import com.bridgelabz.fundooNotes.user.dto.UserDto;
import com.bridgelabz.fundooNotes.user.exception.UserException;
import com.bridgelabz.fundooNotes.user.response.UserResp;
import com.bridgelabz.fundooNotes.user.response.UserResponse;

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




