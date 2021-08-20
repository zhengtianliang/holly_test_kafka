package com.testkafka.config;

import com.testkafka.listener.MyKafkaListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ZhengTianLiang
 * @date: 2021/8/19  17:53
 * @desc: kafka消息提供方的配置类
 */

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    // 是kafka的消息的服务器地址
    private String brokers = "10.3.7.185:9092";

    // 自动提交
    private boolean consumerEnableAutoCommit = true;

    // 自动提交的过期时间    1000毫秒
    private String consumerAutoCommitMs = "1000";

    // session的过期时间
    private String consumerSessionTimeoutMs = "30000";

    // key的序列化方式
    private String consumerKeyDeserializer = "org.apache.kafka.common.serialization.StringDeserializer";

    // value的序列化方式
    private String consumerValueDeserializer = "org.apache.kafka.common.serialization.StringDeserializer";


    /**
     * @author: ZhengTianLiang
     * @date: 2021/8/19  17:12
     * @desc: 创建一个kafka监听容器的工厂的bean对象。
     */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,String>> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,String> factory =new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setPollTimeout(1500);
        return factory;
    }

    // 创建一个消费者工厂，通过这个工厂去创建消费者
    public ConsumerFactory<String,String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    // 准备配置类的map参数
    public Map<String,Object> consumerConfigs(){
        Map<String,Object> properties = new HashMap<>();
        properties.put("bootstrap.servers",brokers);
        properties.put("group.id","group_test_1");
        properties.put("enable.auto.commit",consumerEnableAutoCommit);
        properties.put("auto.commit.interval.ms",consumerAutoCommitMs);
        properties.put("auto.offset.reset","earliest"); // 若kafka突发宕机了，这个earliest代表只恢复未消费的消息，已消费的不恢复
        properties.put("session.timeout.ms",consumerSessionTimeoutMs);
        properties.put("key.deserializer",consumerKeyDeserializer);
        properties.put("value.deserializer",consumerValueDeserializer);

        return properties;
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/8/19  17:29
     * @desc: 自定义监听
     */
    @Bean
    public MyKafkaListener listener(){
        return new MyKafkaListener();
    }

}
