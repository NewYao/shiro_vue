package cn.jnx.controller;

import javax.jms.Queue;
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jnx.model.ReturnJson;

@RestController
@RequestMapping("/chat/prod")
public class ChatProducerController {
    //注入点对点的模式（Queue模式）
    @Autowired
    private Queue queue;
 
    //注入订阅模式（Topic）的消息
    @Autowired
    private Topic topic;
 
    //注入springboot封装的工具类
    @Autowired
    private JmsMessagingTemplate jms;
 
 
    /**
     * 点对点模式（queue）模式发消息
     * @param text
     */
    @PostMapping("/queueSend")
    public ReturnJson queueSend(String text) {
        //发送消息至消息中间件代理（Broker）
        jms.convertAndSend(queue, text);
        return new ReturnJson().ok().message("queue发送成功！");
    }
 
    /**
     * 订阅模式（topic）发送消息
     * @param text
     * @return
     */
    @PostMapping("/topicSend")
    public ReturnJson topicSend(String text){
        jms.convertAndSend(topic,text);
        return new ReturnJson().ok().message("topic发送成功！");
    }

}
