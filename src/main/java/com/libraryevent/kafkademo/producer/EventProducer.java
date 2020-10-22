package com.libraryevent.kafkademo.producer;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libraryevent.kafkademo.domain.LibraryEvent;

import jdk.internal.org.jline.utils.Log;

@Component
public class EventProducer {

Logger logger=LoggerFactory.getLogger(getClass());	

@Autowired
ObjectMapper objMapper;

@Autowired
KafkaTemplate<Integer,String> kafkaTemplate;

String topic="library-events";

public void sendLibraryEvents(LibraryEvent libraryevent) throws JsonProcessingException {
	
	
	
	Integer key=libraryevent.getLibraryEventId();
	String value=objMapper.writeValueAsString(libraryevent.getBook());
	
	ListenableFuture<SendResult<Integer,String>> listenableFuture=kafkaTemplate.sendDefault(key,value);
	listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer,String>>() {

		@Override
		public void onSuccess(SendResult<Integer, String> result) {
			onSuccessMethod(key,value,result);
			
		}
		@Override
		public void onFailure(Throwable ex) {
			onFailureMethod(key,value,ex);
			
		}
	
	});

}

public void sendLibraryEvent_Appraoch(LibraryEvent libraryevent) throws JsonProcessingException {
	Integer key= libraryevent.getLibraryEventId();
	String value=objMapper.writeValueAsString(libraryevent);
	ProducerRecord<Integer,String> producerRecord=buildProducerRecord(key,value,topic);
	ListenableFuture<SendResult<Integer,String>> listenableFuture=kafkaTemplate.send(producerRecord);
	listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer,String>>() {

		@Override
		public void onSuccess(SendResult<Integer, String> result) {
			onSuccessMethod(key,value,result);
			
		}
		@Override
		public void onFailure(Throwable ex) {
			onFailureMethod(key,value,ex);
			
		}
	
	});
	
  }


private ProducerRecord<Integer, String> buildProducerRecord(Integer key, String value, String topic2) {
	
	return new ProducerRecord<>(topic2, key,value);
}

public SendResult<Integer, String> sendLibraryEventsSynchronous(LibraryEvent libraryevent) throws JsonProcessingException {
	Integer key= libraryevent.getLibraryEventId();
	String value=objMapper.writeValueAsString(libraryevent);
	SendResult<Integer,String> sendResult=null;
	try {
		sendResult=kafkaTemplate.sendDefault(key,value).get();//synchronous result;
		
	}catch(ExecutionException |InterruptedException e) {
		logger.error("execution exception occured"+e.getMessage());
	}catch(Exception e) {
		e.printStackTrace();
	}
	return sendResult;
}


private void onSuccessMethod(Integer key, String value, SendResult<Integer, String> result) {
	logger.info("successfully message sent "+key+" "+value+" "+result.getProducerRecord().partition());
	
}

private void onFailureMethod(Integer key, String value, Throwable ex) {
	try {
		throw ex;
	}catch(Throwable throwable) {
		logger.error("error::"+throwable);
	}
	
}
	
}
