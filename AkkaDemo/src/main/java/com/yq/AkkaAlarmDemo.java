package com.yq;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.yq.actor.ClearAlarmAction;
import com.yq.actor.CreateAlarmAction;
import com.yq.actor.FilterScript;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class AkkaAlarmDemo {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("AlarmDemo");
        try {

            final ActorRef createAlarmActor =
                    system.actorOf(CreateAlarmAction.props(), "createAlarmActor");
            final ActorRef clearAlarmActor =
                    system.actorOf(ClearAlarmAction.props(), "clearAlarmActor");
            final ActorRef filterScript =
                    system.actorOf(FilterScript.props("filterScriptActor", createAlarmActor, clearAlarmActor), "filterScript");


            //#create-actors

            //#main-send-messages
            Map<String, Object> sensorDataMap = new HashMap<>();
            sensorDataMap.put("temperature", 60);
            sensorDataMap.put("humidity", 20);

            FilterScript.DeviceDataEvent deviceDataAndRule = new FilterScript.DeviceDataEvent("device001", sensorDataMap);
            filterScript.tell(deviceDataAndRule, ActorRef.noSender());


            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        } catch (IOException ioe) {
        } finally {
            system.terminate();
        }
    }
}
