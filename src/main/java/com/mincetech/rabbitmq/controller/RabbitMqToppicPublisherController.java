package com.mincetech.rabbitmq.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping(value = "/rabbitmq/topic")
public class RabbitMqToppicPublisherController {

//	@Autowired
//	private AmqpTemplate amqpTemplate;
	
	
	@Autowired
	private RabbitTemplate template;
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@GetMapping(value = "/count")
	public String OrderCount(@RequestParam("messageData") String messageData) {
		logger.info("start   {}",messageData);
		String exchangeName="topic-exchange";
		String routingKey="queue.orderCount";
		
		template.convertAndSend(exchangeName, routingKey, messageData);

		return "Message sent to the RabbitMQ Exchange Successfully";
	}
	@GetMapping(value = "/Notification")
	public String Notification(@RequestParam("messageData") String messageData) {
		logger.info("start   {}",messageData);
		String exchangeName="topic-exchange";
		String routingKey="queue.orderNotification";
		
		template.convertAndSend(exchangeName, routingKey, messageData);

		return "Message sent to the RabbitMQ Exchange Successfully";
	}
	@GetMapping(value = "/admin-producer")
	public String Admin() {
		
		String exchangeName="topic-exchange";
		String routingKey="queue.admin";
		String messageData="Welcome to This Project";
		
		template.convertAndSend(exchangeName, routingKey, messageData);

		return "Message sent to the RabbitMQ Exchange Successfully";
	}
}
