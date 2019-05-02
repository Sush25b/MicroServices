package com.bridgelabz.fundooUser.user.service;

public interface RabbitMqSender 
{
	void sendMessageToQueue(String message);
}
