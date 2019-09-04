package com.yq.jsdemo;


import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Simple to Introduction
 * className: JavaJSDemo
 *
 * @author EricYang
 * @version 2018/12/22 9:51
 */

@Slf4j
public class JavaJSListDemo {

    private static final String JS_ENGINE_NAME= "nashorn";
    private final ScriptEngineManager sem = new ScriptEngineManager();
    private final ScriptEngine engine = sem.getEngineByName(JS_ENGINE_NAME);

    public static void main(String[] args) {
        JavaJSListDemo demo = new JavaJSListDemo();

        demo.invokeFunctionByFileDemo();
        demo.invokeFunctionByFileWithParams();
        demo.invokeFunctionByFileWithListParams();
    }

    private void invokeFunctionByFileDemo() {
        log.info("---          invokeFunction         ---" );
        try {
            log.info("current dir={}", System.getProperty("user.dir"));
            File file = new File("./javademo/src/main/resources/demo.js");
            Reader reader = Files.newBufferedReader(file.toPath(), Charset.defaultCharset());

            engine.put("user", "{name:'张三',age:18,city:['陕西','台湾']};");
            Object obj1 = engine.eval(reader);

            Invocable jsInvoke = (Invocable) engine;
            Object obj2 = jsInvoke.invokeFunction("myFunc");

            log.info("function={}, result={}", obj1, obj2);
        }
        catch(Exception ex) {
            log.warn("exception={}", ex.getMessage(), ex);
        }
    }

    private void invokeFunctionByFileWithParams() {
        log.info("---          invokeFunctionWithParams         ---" );
        try {
            log.info("Current dir={}", System.getProperty("user.dir"));
            File file = new File("./javademo/src/main/resources/demoWithParams.js");
            Reader reader = Files.newBufferedReader(file.toPath(), Charset.defaultCharset());

            Object obj1 = engine.eval(reader);

            Invocable jsInvoke = (Invocable) engine;
            JSONObject user = new JSONObject();
            user.put("name", "张三");
            user.put("age", 18);
            Object obj2 = jsInvoke.invokeFunction("myFunc", user,3);

            log.info("function={}, result={}", obj1, obj2);
        }
        catch(Exception ex) {
            log.warn("exception", ex);
        }
    }

    private void invokeFunctionByFileWithListParams() {
        log.info("---         With List Params         ---" );
        try {
            log.info("Current dir={}", System.getProperty("user.dir"));
            File file = new File("./javademo/src/main/resources/demoWithListParams.js");
            Reader reader = Files.newBufferedReader(file.toPath(), Charset.defaultCharset());

            Object obj1 = engine.eval(reader);

            Invocable jsInvoke = (Invocable) engine;
            List<Short> dataList = new ArrayList<>();
            //15,01,00,00,00,0x0B,01,03,08,01,83,0x63,0x88,02,03,0xEA,0x1B
            dataList.add((short)0x15);
            dataList.add((short)0x01);
            dataList.add((short)0x00);
            dataList.add((short)0x00);
            dataList.add((short)0x00);
            dataList.add((short)0x0B);
            dataList.add((short)0x01);
            dataList.add((short)0x03);
            dataList.add((short)0x08);

            dataList.add((short)0x01);
            dataList.add((short)0x83);
            dataList.add((short)0x63);
            dataList.add((short)0x88);


            dataList.add((short)0x02);
            dataList.add((short)0x03);
            dataList.add((short)0xEA);
            dataList.add((short)0x1B);


            Object obj2 = jsInvoke.invokeFunction("myFunc", Arrays.toString(dataList.toArray()), "device1234");
            Object obj3 = null;
            //obj3 = jsInvoke.invokeFunction("myFunc", "[123]", 3);

            log.info("function={}, result={}, {}", obj1, obj2, obj3);
            log.info("result={}, {}", obj2, obj3);
        }
        catch(Exception ex) {
            log.warn("exception", ex);
        }
    }
}

