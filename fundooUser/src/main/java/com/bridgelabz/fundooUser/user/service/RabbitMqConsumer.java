package com.bridgelabz.fundooUser.user.service;

public interface RabbitMqConsumer 
{
	void reciveMessage(String message);
	String getMessage();
}
