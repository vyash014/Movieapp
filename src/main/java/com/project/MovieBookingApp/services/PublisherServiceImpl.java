package com.project.MovieBookingApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService {
	
    public static final String topic = "movieapp";

    @Autowired
    private KafkaTemplate<String,String> template;

    @Override
    public KafkaTemplate<String, String> getTemp() {
        return template;
    }

    @Override
    public void setTemp(String message) {
        this.template.send(topic, message);
    }

    public static String getTopic(){
        return topic;
    }
}
