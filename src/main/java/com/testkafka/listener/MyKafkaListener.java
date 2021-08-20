package com.testkafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author: ZhengTianLiang
 * @date: 2021/8/19  17:47
 * @desc: kafka的监听器
 */

@Slf4j
@Component
public class MyKafkaListener {

    /**
     * @author: ZhengTianLiang
     * @date: 2021/8/19  17:49
     * @desc: 发送消息时的监听
     */
    @KafkaListener(topics = {"topic_1"})   // 应该是队列的名称
    public void sendListener(ConsumerRecord<?,?> record){
        System.out.println(123);
        log.info("messageWebSocket发送消息监听:"+record.value().toString());

    }

}
