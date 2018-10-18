package com.yq.exec;


import lombok.extern.slf4j.Slf4j;

/**
 * Simple to Introduction
 * className: MainOne
 *
 * @author EricYang
 * @version 2018/9/28 9:15
 */
@Slf4j
public class MainOne {

    public static void main(String[] args) throws Exception {
        log.info("starting");
        String str1 = "{abc}";
        String str2 = "{abcs}";
        log.info("str1 hashcode={}", str1.hashCode());
        log.info("str1 identify={}", System.identityHashCode(str1));
        log.info("str1 hex identify={}", Integer.toHexString(System.identityHashCode(str1)));

        MainOne obj = new MainOne();
        log.info("obj hashcode={}", obj.hashCode());
        log.info("obj identify={}", System.identityHashCode(obj));
        log.info("obj hex identify={}", Integer.toHexString(System.identityHashCode(obj)));
        log.info("obj toString={}", obj.toString());
        log.info("str2 hashcode={}", str2.hashCode());
        //public ClientException(int errorCode, String message, Throwable chainedException)

        try {
            Exception ex = new Exception("nihao", null);
            throw ex;
        } catch (Exception ex) {
            //log.error("Error msg={}", ex.getMessage(), ex);
            //log.error("Error msg2={}", ex.getMessage());
        }

    }
}
