package com.apirest.webflux.kafka;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.apirest.webflux.util.RestUtil;

@Service
public class ConsumerKafka {
	@Value("${topic.name.consumer}")
	private String topicName;
	
	private List<String> listMsg = new ArrayList<String>();

	@KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
	public void consume(ConsumerRecord<String, String> payload) {
		System.out.println("Recebendo mensagem: \nTopico: " + topicName 
				+ "\n Key: " + payload.key() 
				+ "\n Headers: " + payload.headers() 
				+ "\n Partition: " + payload.partition() 
				+ "\n Order: " + payload.value());
		
		listMsg.add(payload.value());
		postMessage(payload.value());
	}
	
	public List<String> getMessages() {
		return listMsg;
	}
	
	public void postMessage(String msg) {
		
		RestUtil restUtil = new RestUtil();
		restUtil.sendPost(msg);
	
	}
		
}
