package com.testkafka.config;

import com.sun.tracing.ProviderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ZhengTianLiang
 * @date: 2021/8/19  17:02
 * @desc: kafka的服务提供方配置类
 */

@Configuration
@EnableKafka
public class KafkaProviderConfig {

    // 存消息的服务器地址？
    private String brokers = "10.3.7.185:9092";

    // 重试次数
    private Integer producerRetries = 0;

    // 批量提交的时候的size大小
    private Integer producerBatchSize = 131072;

    //
    private Integer producerLinger_ms = 100;

    // provider的缓存大小
    private Integer producerBufferMemory = 67108864;

    // key的序列化方式
    private String producerKeyDeserializer = "org.apache.kafka.common.serialization.StringSerializer";

    // value的序列化方式
    private String producerValueDeserializer = "org.apache.kafka.common.serialization.StringSerializer";

    /**
     * @author: ZhengTianLiang
     * @date: 2021/8/19  18:04
     * @desc: 准备provider的config
     */
    public Map<String,Object> providerConfig(){
        Map<String,Object> properties = new HashMap<>();
        properties.put("bootstrap.servers",brokers);
//        properties.put("acks","all"); // ack的消息确认。ack是判别请求是否为完整的条件（就是是判断是不是成功发送了）。我们指定了“all”将会阻塞消息，这种设置性能最低，但是是最可靠的。
        properties.put("retries",producerRetries); //如果请求失败，生产者会自动重试，我们指定是0次，如果启用重试，则会有重复消息的可能性。
        properties.put("batch.size",producerBatchSize); //provider缓存每个分区的未发送消息  缓存的大小是通过 batch.size 配置指定的 16384
        properties.put("linger.ms",producerLinger_ms);
        properties.put("buffer.memory",producerBufferMemory);
        properties.put("key.serializer",producerKeyDeserializer);
        properties.put("value.serializer",producerValueDeserializer);

        return properties;
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/8/20  9:53
     * @desc: 创建一个provider的工厂
     */
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(providerConfig());
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/8/20  9:53
     * @desc: 注入一个 kafkaTemplate的对象
     */
    @Bean
    public KafkaTemplate<String,String> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

}
