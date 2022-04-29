package com.apirest.webflux.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.webflux.kafka.ConsumerKafka;
import com.apirest.webflux.kafka.ProducerKafka;

@RestController 
@RequestMapping(value = "/kafka")
public class KafkaTestController {
	
	@Autowired
	ProducerKafka producerKafka;
	
	@Autowired
	ConsumerKafka consumerKafka;
	
	@PostMapping(value = "/send")
	public void send(@RequestBody String msg) {
		producerKafka.send(msg);
	}
	
	@GetMapping(value = "/messages")
	public List<String> getMessage() {
		return consumerKafka.getMessages();
	}
}
