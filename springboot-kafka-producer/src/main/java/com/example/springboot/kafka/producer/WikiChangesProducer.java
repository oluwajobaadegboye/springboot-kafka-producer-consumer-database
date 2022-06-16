package com.example.springboot.kafka.producer;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;


@Service
public class WikiChangesProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WikiChangesProducer.class);

    @Value(value = "${kafka.topic.name}")
    private String topicName;

    @Value(value = "${kafka.stream.source.url}")
    private String kafkaStreamSourceUrl;

    private KafkaTemplate<String, String> kafkaTemplate;

    public WikiChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        EventHandler eventHandler = new WikiChangesHandler(kafkaTemplate, topicName);
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(kafkaStreamSourceUrl));
        EventSource eventSource = builder.build();
        eventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }
}
