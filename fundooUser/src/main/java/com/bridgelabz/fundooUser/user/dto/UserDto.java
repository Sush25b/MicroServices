package com.bridgelabz.fundooNotes.user.dto;

import javax.persistence.Column;

public class UserDto 
{
	private String firstName;
	
	private String lastName;

	private String emailid;
	
	private String phoneno;
	
	private String password;
	
	public UserDto() {
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserDto [firstName=" + firstName + ", lastName=" + lastName + ", emailid=" + emailid + ", phoneno="
				+ phoneno + ", password=" + password + "]";
	}
	
	
	
}
