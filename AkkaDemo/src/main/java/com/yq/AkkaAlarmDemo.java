package com.yq;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.yq.context.IoTContext;
import com.yq.ruleActor.CreateAlarmActionActor;
import com.yq.ruleActor.FilterScriptActor;
import com.yq.ruleActor.RestHttpActor;
import com.yq.ruleActor.SendMailActionActor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class AkkaAlarmDemo {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("AlarmDemo");
        try {

            IoTContext ioTContext = new IoTContext();

            final ActorRef filterScriptActor =
                    system.actorOf(FilterScriptActor.props(ioTContext), "filterScriptActor");

            final ActorRef createAlarmActor =
                    system.actorOf(CreateAlarmActionActor.props(ioTContext), "createAlarmActionActor");
            final ActorRef sendMailActor =
                    system.actorOf(SendMailActionActor.props(ioTContext), "sendMailActor");
            final ActorRef callRestActor =
                    system.actorOf(RestHttpActor.props(ioTContext), "callRestActor");


            //#create-actors
            //#main-send-messages
            Map<String, Object> sensorDataMap = new HashMap<>();
            sensorDataMap.put("temperature", 60);
            sensorDataMap.put("humidity", 20);

            FilterScriptActor.DeviceDataEvent deviceDataAndRule =
                    new FilterScriptActor.DeviceDataEvent("device001", sensorDataMap, null, null);
            filterScriptActor.tell("other Message", ActorRef.noSender());



            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        } catch (IOException ioe) {
        } finally {
            system.terminate();
        }
    }
}
