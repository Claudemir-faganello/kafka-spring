package com.claudemir.strproducer.config;

import java.util.HashMap;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class KafkaAdminConfig {

    private final KafkaProperties kafkaProperties;

    @Bean
    public KafkaAdmin kafkaAdmin () {
        final HashMap< String, Object > configs = new HashMap<>();
        configs.put( AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, this.kafkaProperties.getBootstrapServers() );
        return new KafkaAdmin( configs );
    }

    @Bean
    public KafkaAdmin.NewTopics createTopic () {
        return new KafkaAdmin.NewTopics(
            TopicBuilder.name( "str-topic" ).partitions( 2 ).replicas( 1 ).build()
        );
    }
}
