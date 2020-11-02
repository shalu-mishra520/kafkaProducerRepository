package com.libraryevent.kafkademo.RecordProducer;

import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericData.Record;
import org.apache.avro.generic.GenericRecordBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.libraryevent.kafkademo.domain.Customer;
import com.libraryevent.kafkademo.schema.SchemaRepository;


@Component
public class CustomerRecord {

	Logger logger =LoggerFactory.getLogger(getClass());
	
	
	public Record avroSchemaObject(Customer customer) {
		
		logger.info("avro schema object");
		
		GenericRecordBuilder customerBuilder= new GenericRecordBuilder(SchemaRepository.schemaDefinition());
		customerBuilder.set("first_name",customer.getFirst_name());
		customerBuilder.set("last_name",customer.getLast_name());
		customerBuilder.set("age",customer.getAge());
		customerBuilder.set("height",customer.getHeight());
		customerBuilder.set("weight",customer.getWeight());
		customerBuilder.set("automated_email",customer.isAutomated_email());
		
		logger.info("after setting the customer record");
		
		GenericData.Record myCustomer = customerBuilder.build();
		return myCustomer;

	}
	
	
	
}
