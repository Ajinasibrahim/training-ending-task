package com.mincetech.rabbitmq.Listner;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class MessageListner {
	@RabbitListener(queues = "adminQueue")
	public void admin(String msg)
	{
		System.out.println("hai  "+ msg);
	}
	
	@RabbitListener(queues = "orderCountQueue")
	public void count(String count)
	{
		System.out.println(count);
	}
	
	@RabbitListener(queues = "orderNotificationQueue")
	public void Notification(String msg)
	{
		System.out.println(msg);
	}
}
