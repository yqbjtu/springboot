package com.yq.controller;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Simple to Introduction
 * className: MainDemoTwo
 *
 * @author EricYang
 * @version 2018/9/10 10:18
 */
public class MainDemoTwo {
    public static void main(String[] args) {
        String[] msgIdList = {"a", "B"};
        List<String> list = Arrays.asList(msgIdList);
        String str = list.toString();
        System.out.println(str);

        str = StringUtils.join(list.toArray(), "','");
        String strIds = StringUtils.join(msgIdList, "','");
        int aaa = strIds.hashCode();
        String objInteger = Integer.toHexString(System.identityHashCode(strIds));
        System.out.println("objInteger:" + objInteger);

        StringBuffer strBuf = new StringBuffer();

        strBuf.append("'").append(strIds).append("'");
        System.out.println(str);
        System.out.println("strIds: " + strBuf.toString());
        int bbb = strBuf.hashCode();
        int bufferHashCode = System.identityHashCode(strBuf);
        objInteger = Integer.toHexString(System.identityHashCode(strBuf));
        System.out.println("objInteger:" + objInteger);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz");
        Date now = new Date();
        String timeStr = format.format(now);
        System.out.println("Current time : " + timeStr);
        long time = System.currentTimeMillis();
        System.out.println(" time : " + time);

        Integer myInt = Integer.valueOf(2);
        System.out.println(" myInt : " + myInt);

        long criteria = 1000 * 60 * myInt;
        System.out.println(" criteria : " + criteria);

    }

}
