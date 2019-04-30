package com.bridgelabz.fundooNotes.user.service;

public interface RabbitMqConsumer 
{
	void reciveMessage(String message);
	String getMessage();
}
