package com.yq.js;

import com.alibaba.fastjson.JSONObject;
import delight.nashornsandbox.NashornSandbox;
import delight.nashornsandbox.NashornSandboxes;
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
import java.util.concurrent.Executors;


/**
 * Simple to Introduction
 * className: JavaJSDemo
 *   https://github.com/javadelight/delight-nashorn-sandbox
 * @author EricYang
 * @version 2018/12/22 9:51
 */

@Slf4j
public class JavaJSSandBoxDemo {
    private NashornSandbox sandbox = NashornSandboxes.create();

    public static void main(String[] args) {
        JavaJSSandBoxDemo demo = new JavaJSSandBoxDemo();
        
        demo.bindingDemo();
        demo.invokeFunctionDemo();

    }

    /*
     * 初始化sandbox，限制它使用的资源
     */
    JavaJSSandBoxDemo() {
        sandbox.setMaxCPUTime(100);
        //sandbox.setMaxMemory(200 * 1024);
        sandbox.allowNoBraces(false);
        sandbox.setMaxPreparedStatements(30); // because preparing scripts for execution is expensive
        sandbox.setExecutor(Executors.newSingleThreadExecutor());
    }

    public void bindingDemo() {
        try {
            final Bindings bindings = sandbox.createBindings();
            bindings.put("$ARG","hello world!");
            Object result = sandbox.eval("$ARG", bindings);
            log.info("result=" + result);


            bindings.put("k",20);
            result = sandbox.eval("k + 1", bindings);
            log.info("result=" + result);


            result = sandbox.eval("n = 1738");
            log.info("result=" + result);
            result = sandbox.get("n");
            log.info("result=" + result);

            Bindings scope = sandbox.createBindings();
            scope.put("key", "西安");
            result = sandbox.eval("key + '市'", scope);
            log.info("result=" + result);
        }
        catch (ScriptException se) {
           log.warn("binding demo exception.", se);
        }
    }

//    public void reDirectIODemo() {
//        log.info("---          redirect IO          ---" );
//        try {
//            StringWriter writer = new StringWriter();
//            sandbox.getContext().setWriter(writer);
//            sandbox.put("msg", "hello world!");
//            //任何一print函数输出的内容都会送到writer对象中
//            Object result = sandbox.eval("print(msg)");
//            log.info("result=" + result);
//            log.info("result=" + writer.toString());
//
//            //js中直接调用java的System.out.println
//             result = sandbox.eval("java.lang.System.out.println(msg)");
//            log.info("result=" + result);
//            log.info("result=" + writer.toString());
//
//        }
//        catch (ScriptException se) {
//            log.warn("binding demo exception.", se);
//        }
//    }

    public boolean invokeFunctionDemo() {
        log.info("---          invokeFunction         ---" );
        boolean result = true;
        try {

            final Bindings bindings = sandbox.createBindings();
            bindings.put("msg","hello world!");
            String str = "var user = {name:'张三',age:18,city:['陕西','台湾']};";
            sandbox.eval(str, bindings);

            log.info("Get msg={}", sandbox.eval("msg", bindings));
            //获取变量
            sandbox.eval("var sum = eval('1 + 2 + 3*4');");
            //调用js的eval的方法完成运算
            log.info("get sum={}", sandbox.get("sum"));

            JSONObject msg = new JSONObject();
            msg.put("temperature", 32);
            msg.put("humidity", 20);
            msg.put("voltage", 220);
            msg.put("electricity", 13);

            JSONObject metadata = new JSONObject();
            metadata.put("deviceName", "空气质量检测器01");
            metadata.put("contacts", "张三");

            JSONObject msgType = new JSONObject();
            msgType.put("type", "deviceTelemetryData1");

            //定义函数
            String func = "var result = true; \r\n" +
                    "if (msgType.type = 'deviceTelemetryData') { \r\n" +
                    "   if (msg.temperature >0 && msg.temperature < 33) { \n       result = true ;}  \n" +
                    "   else { \n       result = false;}  \n" +
                    "} else { \n     result = false;  \n" +
                    "     var errorMsg = msgType.type + ' is not deviceTelemetryData';  \n" +
                    "     print(msgType.type) \n } \n\n" +
                    "return result";
            log.info("func = {}", func);
            sandbox.eval("function filter(msg, metadata, msgType){ " + func + "}");
            // 执行js函数
            Object obj  = sandbox.getSandboxedInvocable().invokeFunction("filter", msg, metadata, msgType);

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

/*
 sandbox.setMaxMemory(50*1024);

14:18:55.304 [pool-1-thread-1] DEBUG delight.nashornsandbox.NashornSandbox - --- JS END ---
14:18:55.312 [main] WARN com.yq.js.JavaJSSandBoxDemo - exception
java.lang.ClassCastException: delight.nashornsandbox.internal.NashornSandboxImpl cannot be cast to javax.script.Invocable
	at com.yq.js.JavaJSSandBoxDemo.invokeFunctionDemo(JavaJSSandBoxDemo.java:146)
	at com.yq.js.JavaJSSandBoxDemo.main(JavaJSSandBoxDemo.java:39)

Process finished with exit code 1
 */