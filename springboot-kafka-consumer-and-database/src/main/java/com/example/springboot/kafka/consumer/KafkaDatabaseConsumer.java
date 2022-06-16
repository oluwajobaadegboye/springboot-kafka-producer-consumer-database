package com.example.springboot.kafka.consumer;

import com.example.springboot.kafka.consumer.entity.KafkaDataEntity;
import com.example.springboot.kafka.consumer.repository.KafkaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);
    @Autowired
    private KafkaDataRepository kafkaDataRepository;

    @KafkaListener(
            topics = "${kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String eventMessage){
        LOGGER.info(String.format("Message Received -> %s", eventMessage));

        kafkaDataRepository.save(
                KafkaDataEntity.builder()
                        .eventMessage(eventMessage)
                        .build()
        );
    }
}
