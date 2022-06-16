package com.example.springboot.kafka.consumer.repository;

import com.example.springboot.kafka.consumer.entity.KafkaDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KafkaDataRepository extends JpaRepository<KafkaDataEntity, Long> {
}
