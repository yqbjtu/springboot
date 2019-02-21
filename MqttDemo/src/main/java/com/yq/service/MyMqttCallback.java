package com.yq.service;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Simple to Introduction
 * className: MyMqttCallback
 *
 * @author EricYang
 * @version 2019/2/21 10:34
 */
@Slf4j
public class MyMqttCallback implements MqttCallback {
    //threadIdClass和controller的本地运行的线程id一致，每次请求都会在新线程中调用controller对象， controller对象只有一个
    long threadIdClass = Thread.currentThread().getId();

    public void connectionLost(Throwable cause) {
        //如果设置了自动重连，会自动重连。而且会自动订阅之前的topic
        log.warn("connectionLost.", cause);
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        log.info("deliveryComplete", token.isComplete());
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        long threadId = Thread.currentThread().getId();
        // 订阅topic后，如果有消息到达，会接收到消息
        log.info("msg arrived. topic={}, QoS={}, msgBody={}. threadIdClass={}, threadId={}", topic, message.getQos(), new String(message.getPayload()),
                threadIdClass, threadId);
    }

}
