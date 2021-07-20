package com.mincetech.rabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMqTopicConfig {
	
	@Bean
	Queue orderCountQueue() {
		return new Queue("orderCountQueue", false);
	}

	@Bean
	Queue orderNotificationQueue() {
		return new Queue("orderNotificationQueue", false);
	}
	@Bean
	Queue adminQueue() {
		return new Queue("adminQueue", false);
	}

	@Bean
	TopicExchange topicExchange() {
		return new TopicExchange("topic-exchange");
	}
	@Bean
	Binding orderCountBinding(Queue orderCountQueue, TopicExchange topicExchange) {
		return BindingBuilder.bind(orderCountQueue).to(topicExchange).with("queue.orderCount");
	}
	
	@Bean
	Binding takeaziBinding(Queue orderNotificationQueue, TopicExchange topicExchange) {
		return BindingBuilder.bind(orderNotificationQueue).to(topicExchange).with("queue.orderNotification");
	}
	
	@Bean
	Binding adminBinding(Queue adminQueue, TopicExchange topicExchange) {
		return BindingBuilder.bind(adminQueue).to(topicExchange).with("queue.admin");
	}
	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	@Bean
	public AmqpTemplate template(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate= new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(messageConverter());
		return rabbitTemplate;
	}
}
