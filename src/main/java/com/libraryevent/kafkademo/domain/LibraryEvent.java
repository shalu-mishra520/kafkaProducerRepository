package com.libraryevent.kafkademo.domain;

import org.springframework.lang.NonNull;

public class LibraryEvent {
	
	private  Integer libraryEventId;
	private LibraryEventType eventType;
	
	@NonNull
	private Book book;

	public LibraryEvent(Integer libraryEventId, Book book) {
		super();
		this.libraryEventId = libraryEventId;
		this.book = book;
	}

	public LibraryEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getLibraryEventId() {
		return libraryEventId;
	}

	public void setLibraryEventId(Integer libraryEventId) {
		this.libraryEventId = libraryEventId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LibraryEventType getEventType() {
		return eventType;
	}

	public void setEventType(LibraryEventType eventType) {
		this.eventType = eventType;
	}
	
	
}
