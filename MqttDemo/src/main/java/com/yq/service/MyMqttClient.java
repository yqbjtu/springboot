package com.yq.service;

import com.yq.config.MyMQTTConfig;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String subscribe(String topic, String clientId) {
        String result = "OK";
        try {
            MqttClient client;
            MqttConnectOptions options;
            // host为主机名，clientId即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            client = new MqttClient(config.getTestServers(), clientId, new MemoryPersistence());
            // MQTT的连接设置
            options = new MqttConnectOptions();
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(true);
            // 设置连接的用户名
            options.setUserName(config.getUsername());
            // 设置连接的密码
            options.setPassword(config.getPassword().toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(config.getConnectTimeout());
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(config.getKeepAliveInterval());

            options.setAutomaticReconnect(true);

            // 设置回调
            client.setCallback(new MyMqttCallback());
            MqttTopic mqttTopic = client.getTopic(topic);
            //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
            options.setWill(mqttTopic, "close".getBytes(), 2, true);

            client.connect(options);
            //订阅消息
            int[] Qos  = {1};

            if (client.isConnected()) {
                String[] topics = {topic};
                client.subscribe(topics, Qos);
            }
            else {
                result = "client is not connected";
            }

        } catch (Exception e) {
            result = e.getMessage();
            log.warn("Failed to connect or subscribe. host={}, topic={}", config.getTestServers(), topic, e);
        }

        return result;
    }
}
