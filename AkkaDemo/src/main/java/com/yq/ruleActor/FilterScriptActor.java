package com.yq.ruleActor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yq.domain.Rule;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Map;

//import org.codehaus.jackson.map.ObjectMapper;

public class FilterScriptActor extends AbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    static public Props props(String message, ActorRef createAlarmActor, ActorRef clearAlarmActor) {
        return Props.create(FilterScriptActor.class, () -> new FilterScriptActor(message, createAlarmActor, clearAlarmActor));
    }

    static public class DeviceDataEvent {
        public final String deviceName;
        public final String deviceId;
        public  Map<String, Object> sensorDataMap;

        public DeviceDataEvent(String deviceName, Map<String, Object> sensorDataMap) {
            this.deviceName = deviceName;
            this.sensorDataMap = sensorDataMap;
            deviceId = (String)sensorDataMap.get("deviceId");
        }
    }


    private final String message;
    private final ActorRef createAlarmActor;
    private final ActorRef clearAlarmActor;
    private String greeting = "";

    public FilterScriptActor(String message, ActorRef createAlarmActor, ActorRef clearAlarmActor) {
        this.message = message;
        this.createAlarmActor = createAlarmActor;
        this.clearAlarmActor = clearAlarmActor;
    }

    /*
    The former will update the greeting state of the Actor and
    the latter will trigger a sending of the greeting to the Printer Actor.
     */
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(DeviceDataEvent.class, deviceDataEvent -> {
                    long threadId = Thread.currentThread().getId();
                    this.greeting = message + ", " + deviceDataEvent.deviceName;
                    log.info("match DeviceDataEvent. receiveBuilder greeting={}, threadId={}", greeting, threadId);

                    //遍历设备的所规则，然后逐条进行运行， 获取规则内容，进行js运算，如果是true就报警，否则就停止
                    Rule rule1 = new Rule();
                    rule1.setId("001");
                    //Filter(msg,  metadata, msgType)
                    rule1.setFunctionContent("return msg.temperature < -40 || msg.temperature > 80 || msg.humidity > 30;");
                    rule1.setDescription("测试温度是否高于37度");


                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonStr = objectMapper.writeValueAsString(deviceDataEvent.sensorDataMap);

                    JSONObject json = JSON.parseObject(jsonStr);
                    boolean result = executeFilterFunction(json, null, "DeviceData",rule1.getFunctionContent());
                    if (result) {
                        createAlarmActor.tell(new CreateAlarmActionActor.AlarmMessage(deviceDataEvent.deviceId, deviceDataEvent.deviceName), getSelf());
                    }
                    else {
                        clearAlarmActor.tell(new SendMailActionActor.ClearAlarmMessage(deviceDataEvent.deviceId, "ruleId"), getSelf());
                    }
                })
                .build();
    }

    private boolean executeFilterFunction(JSONObject msg, JSONObject metadata, String msgType, String func) {
        boolean result = true;
        try {
            ScriptEngineManager sem = new ScriptEngineManager();
            ScriptEngine engine = sem.getEngineByName("javascript");
            System.out.println(engine.getClass().getName());
            engine.put("msg", "hello world!");
            String str = "var user = {name:'张三',age:18,city:['陕西','台湾']};";

            engine.eval(str);
            engine.eval("msg = 'hi !';");
            System.out.println(engine.get("msg"));
            //获取变量
            engine.eval("var sum = eval('1+222+33*4');");
            //调用js的eval的方法完成运算
            System.out.println(engine.get("sum"));
            // 获取变量

            //定义函数
            engine.eval("function filter(msg, metadata, msgType){ " + func + "}");
            // 执行js函数
            Invocable jsInvoke = (Invocable) engine;
            Object obj = jsInvoke.invokeFunction("filter", msg, metadata, msgType);
            //方法的名字，参数
            System.out.println(obj);
            result = (Boolean)obj;
        }
        catch(Exception ex) {
            log.info("exception", ex);
            result = false;
        }

        return result;
    }
}

