package com.yq.jsdemo;


import lombok.extern.slf4j.Slf4j;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.io.Reader;
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
public class JavaJSDelimiterStringDemo {

    private static final String JS_ENGINE_NAME= "nashorn";
    private final ScriptEngineManager sem = new ScriptEngineManager();
    private final ScriptEngine engine = sem.getEngineByName(JS_ENGINE_NAME);

    public static void main(String[] args) {
        JavaJSDelimiterStringDemo demo = new JavaJSDelimiterStringDemo();

        demo.invokeFunctionByFileWithStringParams();
    }

    private void invokeFunctionByFileWithStringParams() {
        log.info("---         With String Params         ---" );
        try {
            log.info("Current dir={}", System.getProperty("user.dir"));
            File file = new File("./javademo/src/main/resources/demoWithStringDelimiterParams.js");
            Reader reader = Files.newBufferedReader(file.toPath(), Charset.defaultCharset());


            Object obj1 = engine.eval(reader);

            Invocable jsInvoke = (Invocable) engine;

            String msgStr1 = "866262040$";
            String msgStr2 = "86626204087853$";
            String msgStr3 = "P866262040878530wsx$";
            Object resultObj1 = jsInvoke.invokeFunction("myFunc", msgStr1, null);
            Object resultObj2 = jsInvoke.invokeFunction("myFunc", msgStr2, null);
            Object resultObj3 = jsInvoke.invokeFunction("myFunc", msgStr3, null);

            log.info("function={}", obj1);
            log.info("result1={}", resultObj1);
            log.info("result2={}", resultObj2);
            log.info("result3={}", resultObj3);
        }
        catch(Exception ex) {
            log.warn("exception", ex);
        }
    }
}

