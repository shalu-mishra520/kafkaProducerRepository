package com.libraryevent.kafkademo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.libraryevent.kafkademo.domain.LibraryEvent;
import com.libraryevent.kafkademo.domain.LibraryEventType;
import com.libraryevent.kafkademo.producer.EventProducer;

@RestController
public class LibraryEventController {
	
	@Autowired
	EventProducer eventProducer;
	
	@PostMapping("v1/libraryevent")
	public ResponseEntity<LibraryEvent> postLibraryEvent(@RequestBody @Validated LibraryEvent libraryevent) throws JsonProcessingException{
		
		//eventProducer.sendLibraryEvents(libraryevent);
		//invoke producer
		//eventProducer.sendLibraryEventsSynchronous(libraryevent);
		libraryevent.setEventType(LibraryEventType.NEW);
		eventProducer.sendLibraryEvent_Appraoch(libraryevent);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(libraryevent);
	}
	
	//put for update
	@PutMapping("v1/libraryeventupdate")
	public ResponseEntity<?> putLibraryEvent(@RequestBody @Validated LibraryEvent libraryevent) throws JsonProcessingException{
		
		if(libraryevent.getLibraryEventId()==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please send the Library Event Id");
		}
		libraryevent.setEventType(LibraryEventType.UPDATE);
		eventProducer.sendLibraryEvent_Appraoch(libraryevent);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(libraryevent);
	}

}
