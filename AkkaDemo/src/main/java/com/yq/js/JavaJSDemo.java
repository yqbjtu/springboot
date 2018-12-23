package com.yq.js;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.experimental.var;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.java.Log;

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
import java.nio.file.Path;


/**
 * Simple to Introduction
 * className: JavaJSDemo
 *
 * @author EricYang
 * @version 2018/12/22 9:51
 */

@Slf4j
public class JavaJSDemo {

    private static final String JS_ENGINE_NAME= "nashorn";
    private final ScriptEngineManager sem = new ScriptEngineManager();
    private final ScriptEngine engine = sem.getEngineByName(JS_ENGINE_NAME);

    public static void main(String[] args) {
        JavaJSDemo demo = new JavaJSDemo();

        demo.bindingDemo();
        demo.reDirectIODemo();
        demo.invokeFunctionDemo();
        demo.invokeFunctionByFileDemo();
    }

    public void bindingDemo() {
        try {
            engine.put("msg", "hello world!");
            Object result = engine.eval("msg");
            log.info("result=" + result);

            engine.put("k", 20);
            result = engine.eval("k + 1");
            log.info("result=" + result);


            result = engine.eval("n = 1738");
            log.info("result=" + result);
            result = engine.get("n");
            log.info("result=" + result);

            Bindings scope = engine.createBindings();
            scope.put("key", "西安");
            result = engine.eval("key + '市'", scope);
            log.info("result=" + result);
        }
        catch (ScriptException se) {
           log.warn("binding demo exception.", se);
        }
    }

    public void reDirectIODemo() {
        log.info("---          redirect IO          ---" );
        try {
            StringWriter writer = new StringWriter();
            engine.getContext().setWriter(writer);
            engine.put("msg", "hello world!");
            //任何一print函数输出的内容都会送到writer对象中
            Object result = engine.eval("print(msg)");
            log.info("result=" + result);
            log.info("result=" + writer.toString());

            //js中直接调用java的System.out.println
             result = engine.eval("java.lang.System.out.println(msg)");
            log.info("result=" + result);
            log.info("result=" + writer.toString());

        }
        catch (ScriptException se) {
            log.warn("binding demo exception.", se);
        }
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
            msg.put("electricity", 13);

            JSONObject metadata = new JSONObject();
            metadata.put("deviceName", "空气质量检测器01");
            metadata.put("contacts", "张三");

            JSONObject msgType = new JSONObject();
            //msgType.put("type", "deviceTelemetryData");
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
            engine.eval("function filter(msg, metadata, msgType){ " + func + "}");
            // 执行js函数
            Invocable jsInvoke = (Invocable) engine;
            Object obj = jsInvoke.invokeFunction("filter", msg, metadata, msgType);
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

    public int invokeFunctionByFileDemo() {
        log.info("---          invokeFunction         ---" );
        int result = 0;
        try {
            log.info("Current dir={}", System.getProperty("user.dir"));
            //\AkkaDemo\src\main\resources\demo.js
            File file = new File("./AkkaDemo/src/main/resources/demo.js");

            Reader reader = Files.newBufferedReader(file.toPath(), Charset.defaultCharset());

            engine.put("user", "{name:'张三',age:18,city:['陕西','台湾']};");
            Object obj = engine.eval(reader);

            log.info("get age={}", engine.get("age"));

            log.info("function result={}", obj);

            URL resource = this.getClass().getClassLoader().getResource("demo2.js");
            FileReader fileReader = new FileReader(resource.getPath());
            engine.eval(fileReader);
            //执行js函数
            Invocable jsInvoke = (Invocable)engine;
            obj = jsInvoke.invokeFunction("myAdd", 1, 2);
            log.info("myAdd obj={}", obj);

        }
        catch(Exception ex) {
            log.warn("exception", ex);
        }

        return result;
    }
}
