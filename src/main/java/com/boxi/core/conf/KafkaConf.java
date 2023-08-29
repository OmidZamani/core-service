package com.boxi.core.conf;


import com.boxi.bus.dto.PluralMessage;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@EnableKafka
public class KafkaConf {


    final public String bootstrapAddresses="kafka:9092"; //can add other hosts

/*
# kafka setting
spring.kafka.bootstrapServers:boxi:9092
#spring.kafka.properties.security.protocol=SASL_SSL
#spring.kafka.properties.sasl.mechanism=-SHA-256
#spring.kafka.properties.sasl.jaas.config=org.apache.kafka.common.security.scram.ScramLoginModule required username="${KAFKA_USER}" password="${KAFKA_PASS}";
spring.kafka.consumer.deserializer=org.apache.kafka.common.serialization.LongDeserializer
spring.kafka.consumer.value.deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
spring.kafka.consumer.properties.spring.json.trusted="*"
spring.kafka.consumer.properties.isolation.level=read_committed
spring.kafka.producer.serializer=org.apache.kafka.common.serialization.LongSerializer
spring.kafka.producer.value.deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
#spring.kafka.producer.transaction-id-prefix= tx-
################################################
 */

    @Bean
    public ConsumerFactory<Long , PluralMessage> consumerFactory(){
        Map<String,Object> config=new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapAddresses);
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"sms");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
        config.put(JsonDeserializer.TRUSTED_PACKAGES,"*");
        return new DefaultKafkaConsumerFactory<>(config);
    }



    @Bean
    public KafkaAdmin kafkaAdmin(){
        Map<String,Object> configs=new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapAddresses);
        return new KafkaAdmin(configs);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long,PluralMessage> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<Long,PluralMessage> factory=
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }


}

