package com.bridgelabz.fundooNotes.user.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConsumerImpl implements RabbitMqConsumer 
{
private String message;
	
	//final String queue=environment.getProperty("spring.rabbitmq.template.default-receive-queue");
	
	@Override
	@RabbitListener(queues="${spring.rabbitmq.template.default-receive-queue}")
	public void reciveMessage(String message) 
	{
		this.message=message;	
	}

	@Override
	public String getMessage()
	{	
		return this.message;
	}

}
