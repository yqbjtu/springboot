package com.yq.jsdemo;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.io.Reader;
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
public class JavaJSStringDemo {

    private static final String JS_ENGINE_NAME= "nashorn";
    private final ScriptEngineManager sem = new ScriptEngineManager();
    private final ScriptEngine engine = sem.getEngineByName(JS_ENGINE_NAME);

    public static void main(String[] args) {
        JavaJSStringDemo demo = new JavaJSStringDemo();

        demo.invokeFunctionByFileWithStringParams();
    }


    private void invokeFunctionByFileWithStringParams() {
        log.info("---         With String Params         ---" );
        try {
            log.info("Current dir={}", System.getProperty("user.dir"));
            File file = new File("./javademo/src/main/resources/demoWithStringParams.js");
            Reader reader = Files.newBufferedReader(file.toPath(), Charset.defaultCharset());


            Object obj1 = engine.eval(reader);

            Invocable jsInvoke = (Invocable) engine;

            String msgStr = "ID=P866262040878530;TIME=20190520145720;SIG=19;VBAT=328;NUM=00000125;LEN=000010;abcd123456";

            Object obj2 = jsInvoke.invokeFunction("myFunc", msgStr, null);

            log.info("function={}, result={}", obj1, obj2);
        }
        catch(Exception ex) {
            log.warn("exception", ex);
        }
    }
}

