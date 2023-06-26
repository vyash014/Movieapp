package com.project.MovieBookingApp.services;

import org.springframework.kafka.core.KafkaTemplate;

public interface PublisherService {
	
	public KafkaTemplate<String,String> getTemp();
    public void setTemp(String message);
}
