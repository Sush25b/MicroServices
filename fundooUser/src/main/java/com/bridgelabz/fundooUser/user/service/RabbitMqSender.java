package com.bridgelabz.fundooNotes.user.service;

public interface RabbitMqSender 
{
	void sendMessageToQueue(String message);
}
