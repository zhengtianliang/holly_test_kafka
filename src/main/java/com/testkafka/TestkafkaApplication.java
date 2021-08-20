package com.testkafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class TestkafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestkafkaApplication.class, args);
    }

//    @KafkaListener(topics = {"topic_1"})   // 应该是队列的名称
    public void sendListener(ConsumerRecord<?,?> record){
        System.out.println("11111111111");
        System.out.println("messageWebSocket发送消息监听:"+record.value().toString());
//        log.info();

    }

}
