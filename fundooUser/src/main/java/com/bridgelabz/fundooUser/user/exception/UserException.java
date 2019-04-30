package com.bridgelabz.fundooNotes.user.exception;

public class UserException extends RuntimeException
{
	/**
	 * to set the entity of thrown exception
	 */
	private static final long serialVersionUID = 1L;
	int errorCode;		
	String msg;

	public UserException(String msg) {
		super(msg);
	}

	public UserException(int code, String msg) 
	{
		super(msg);
		this.errorCode = code;
	}
}
