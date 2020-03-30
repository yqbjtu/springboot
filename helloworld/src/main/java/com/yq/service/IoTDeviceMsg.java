package com.yq.service;

import com.aliyun.openservices.iot.api.Profile;
import com.aliyun.openservices.iot.api.message.MessageClientFactory;
import com.aliyun.openservices.iot.api.message.api.MessageClient;
import com.aliyun.openservices.iot.api.message.callback.MessageCallback;
import com.aliyun.openservices.iot.api.message.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Simple to Introduction
 * className: IoTDeviceMsg
 *
 * @author EricYang
 * @version 2019/11/14 14:38
 */
@Service
@Slf4j
public class IoTDeviceMsg {

    // endPoint:  https://${uid}.iot-as-http2.${region}.aliyuncs.com
    String endPoint = "https://" + uid + ".iot-as-http2." + regionId + ".aliyuncs.com";


    void sendMsg() {
        // 连接配置
        Profile profile = Profile.getAccessKeyProfile(endPoint, regionId, accessKey, accessSecret);

        // 构造客户端
        MessageClient client = MessageClientFactory.messageClient(profile);
// 数据接收
        client.connect(messageToken -> {
            Message m = messageToken.getMessage();
            System.out.println("receive message from " + m);
            return MessageCallback.Action.CommitSuccess;
        })
    }
}
