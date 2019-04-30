package com.bridgelabz.fundooNotes.user.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqSenderImpl implements RabbitMqSender 
{
	@Autowired
	private AmqpTemplate rabbitTremplate;
	
	@Autowired
	Environment environment;
	
	@Override
	public void sendMessageToQueue(String message) 
	{
		final String exchange=environment.getProperty("spring.rabbitmq.template.exchange"); 
		final String routingKey=environment.getProperty("spring.rabbitmq.template.routing-key");
		rabbitTremplate.convertAndSend(exchange, routingKey, message);
		
	}
}
