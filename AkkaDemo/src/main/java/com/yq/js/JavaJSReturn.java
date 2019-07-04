package com.yq.js;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;


/**
 * Simple to Introduction
 * className: JavaJSDemo
 *
 * @author EricYang
 * @version 2018/12/22 9:51
 */

@Slf4j
public class JavaJSReturn {

    private static final String JS_ENGINE_NAME= "nashorn";
    private final ScriptEngineManager sem = new ScriptEngineManager();
    //NashornScriptEngineFactory
    private final ScriptEngine engine = sem.getEngineByName(JS_ENGINE_NAME);

    public static void main(String[] args) {
        JavaJSReturn demo = new JavaJSReturn();

        demo.invokeFunctionDemo();
    }

    public boolean invokeFunctionDemo() {
        log.info("---          invokeFunction         ---" );
        boolean result = true;
        try {
            engine.put("msg", "hello world!");
            String str = "var user = {name:'张三',age:18,city:['陕西','台湾']};";
            engine.eval(str);

            log.info("Get msg={}", engine.get("msg"));
            //获取变量
            engine.eval("var sum = eval('1 + 2 + 3*4');");
            //调用js的eval的方法完成运算
            log.info("get sum={}", engine.get("sum"));

            JSONObject msg = new JSONObject();
            msg.put("temperature", 125);
            msg.put("humidity", 20);
            msg.put("voltage", 220);
            msg.put("topic", 13);
            msg.put("id", 13);


            JSONObject metadata = new JSONObject();
            metadata.put("deviceName", "空气质量检测器01");
            metadata.put("contacts", "张三");

            JSONObject msgType = new JSONObject();
            //msgType.put("type", "deviceTelemetryData");
            msgType.put("type", "deviceTelemetryData1");

            //定义函数
            String func = "function invokeInternal_123(msgStr, topicStr) { \n" +
                    "\n" +
                    "    return JSON.stringify(ruleNodeFunc(msgStr, topicStr));\n" +
                    "\n" +
                    "    function ruleNodeFunc(msgStr, topicStr) {\n" +
                    "\t  var msg = JSON.parse(msgStr);\n" +
                    "\t  var dMsg = {};" +
                    "  msg.sensor1=20;\n" +
                    "      dMsg.deviceid=msg.id;\n" +
                    "      dMsg.ts=msg.ts;\n" +
                    "      delete msg.ts;\n" +
                    "\t  delete msg.id; \n" +
                    "      dMsg.topic=topicStr;\n" +
                    "      delete msg.topic;\n" +
                    "\t  dMsg.data={};\n" +
                    "\t  dMsg.data.msg=msg;\n" +
                    "\t  dMsg.data.delta=1;\n" +
                    "\t  return dMsg;\n" +
                    "    }\n" +
                    "}";
            log.info("func = {}", func);
            engine.eval(func);
            // 执行js函数
            Invocable jsInvoke = (Invocable) engine;
            String msgStr = msg.toJSONString();
            String metadataStr = metadata.toJSONString();

            Object obj = jsInvoke.invokeFunction("invokeInternal_123", msgStr, metadataStr);
            //方法的名字，参数
            log.info("function result={}", obj);
            result = (Boolean)obj;
        }
        catch(Exception ex) {
            log.warn("exception", ex);
            result = false;
        }

        return result;
    }

}
