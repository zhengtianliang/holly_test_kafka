package com.testkafka.pojo;

import lombok.Data;

/**
 * @author: ZhengTianLiang
 * @date: 2021/8/20  15:17
 * @desc: mq传输(队列)中的消息体
 */
@Data
public class MsgTransferDTO {

    /**
     * 消息类型(0聊天记录,1实时质检,2意图识别)
     */
    private String type;

    /**
     * 具体的消息体(实体的json字符串格式)
     */
    private String msgContent;
}
