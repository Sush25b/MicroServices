package com.bridgelabz.fundooNotes.user.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseSender 
{
	/**
	 * Send reponse to frontend
	 */
	public static ResponseEntity<UserResponse> sendUserResponse(String msg,int code)
	{
		UserResponse response=new UserResponse();
		
		response.setMessage(msg); //get Message from parent class-->RuntimeException
		response.setStatus(code); 
		response.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	public static ResponseEntity<UserResp> sendUserResp(String msg,int code,String token)
	{
		UserResp response=new UserResp();
		
		response.setMessage(msg); //get Message from parent class-->RuntimeException
		response.setStatus(code); 
		response.setTimeStamp(System.currentTimeMillis());
		response.setToken(token);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
