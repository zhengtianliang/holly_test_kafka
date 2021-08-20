package com.testkafka.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ZhengTianLiang
 * @date: 2021/8/19  17:02
 * @desc:
 */

@RestController
@Slf4j
public class ProviderTest {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping(value = "/test/{mes}")
    public void test1(@PathVariable(value = "mes")String mes){
        log.info("发送消息前");
        kafkaTemplate.send("topic_1",mes);
        log.info("发送消息后");
    }
}
