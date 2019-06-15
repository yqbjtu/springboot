package com.yq.service;

import com.yq.config.MyMQTTConfig;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Simple to Introduction
 * className: MyMqttClient
 *
 * @author EricYang
 * @version 2019/2/21 10:23
 */
@Service
@Slf4j
public class MyMqttClient {
    @Autowired
    MyMQTTConfig  config;

    private MqttConnectOptions getConnectionOptions() {
        // MQTT的连接设置
        MqttConnectOptions options = new MqttConnectOptions();
        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
        options.setCleanSession(true);
        // 设置连接的用户名
        options.setUserName(config.getUsername());
        // 设置连接的密码
        options.setPassword(config.getPassword().toCharArray());
        // 设置超时时间 单位为秒
        options.setConnectionTimeout(config.getConnectTimeout());
        // 设置会话心跳时间 单位为秒 服务器会每隔  秒向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        options.setKeepAliveInterval(config.getKeepAliveInterval());
        options.setAutomaticReconnect(true);

        return options;
    }
    public String subscribe(String topic, String clientId) {
        long threadId = Thread.currentThread().getId();
        String result = "OK. threadId=" + threadId;
        try {
            MqttClient client;

            // host为主机名，clientId即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            client = new MqttClient(config.getTestServers(), clientId, new MemoryPersistence());

            // 设置回调
            client.setCallback(new MyMqttCallback());
            MqttTopic mqttTopic = client.getTopic(topic);
            //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
            //options.setWill(mqttTopic, "close".getBytes(), 2, true);

            MqttConnectOptions options = getConnectionOptions();
            client.connect(options);
            //订阅消息
            int[] Qos  = {1};

            if (client.isConnected()) {
                String[] topics = {topic};
                client.subscribe(topics, Qos);
            }
            else {
                result = "client is not connected. threadId=" + threadId;
            }

        } catch (Exception e) {
            result = e.getMessage();
            log.warn("Failed to connect or subscribe. host={}, topic={}, threadId={}", config.getTestServers(), topic, threadId, e);
        }

        return result;
    }


    public String publish(String topic, String messageBody) {
        long threadId = Thread.currentThread().getId();
        String result = "OK. threadId=" + threadId;
        try {
            // host为主机名，clientId即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            MqttClient client = new MqttClient(config.getTestServers(), UUID.randomUUID().toString(), new MemoryPersistence());

            MqttConnectOptions options = getConnectionOptions();
            client.connect(options);
            //订阅消息

            if (client.isConnected()) {
                MqttMessage message = new MqttMessage();
                message.setQos(2);
                message.setRetained(true);
                message.setPayload(messageBody.getBytes("UTF-8"));
                client.publish(topic, message);
                log.info("publish to host={}, topic={}, threadId={}", config.getTestServers(), topic, threadId);
            }
            else {
                result = "client is not connected. threadId=" + threadId;
            }

        } catch (Exception e) {
            result = e.getMessage();
            log.warn("Failed to connect or publish. host={}, topic={}, threadId={}", config.getTestServers(), topic, threadId, e);
        }

        return result;
    }

}
