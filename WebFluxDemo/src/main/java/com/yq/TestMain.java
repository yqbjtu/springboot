package com.yq;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Simple to Introduction
 * className: TestMain
 *
 * @author EricYang
 * @version 2018/9/2 20:59
 */
@Slf4j
public class TestMain {

    public static void main(String[] args) {
        String jsonStr = "{\"row\":[],\"totalPage\":0,\"totalSize\":0}";
        JSONObject json = (JSONObject) JSONObject.parse(jsonStr);
        int dataRuleCount = (Integer) json.get("totalSize");
        log.info("dataRuleCount={}", dataRuleCount);

        String workerPath ="/myWorkerList/sub-service-8082-1135504170";
        String myConstant = "/myWorkerList";
        String workerId = workerPath.substring(myConstant.length() + 1);
        System.out.println(workerId);

        workerPath ="/myWorkerList/sub-service-8082-1135504170/aa001";
        String uuid = workerPath.substring(workerPath.lastIndexOf("/"));
        System.out.println(uuid);

        int workerCount = 4;
        int taskCount = 13;

        double tmpAvgCount = Math.ceil(taskCount/workerCount);
        System.out.println(tmpAvgCount);

         workerCount = 4;
         taskCount =13;
        double intp = (double)taskCount / (double)workerCount;
        System.out.println(intp);
        System.out.println(Math.ceil(intp));
        tmpAvgCount = Math.ceil(taskCount / workerCount);
        int avgTaskCount = (int)Math.ceil(intp);
        log.info("newTask taskCount={}, workerCount={}, tmpAvgCount={}, avgCount={}", taskCount, workerCount, tmpAvgCount, avgTaskCount);
        long current = 1536021420256L;
        Date date = new Date(current);
        SimpleDateFormat format = new SimpleDateFormat("YYYYMMdd-HHmmss.SSSZZ");
        String currentStr = format.format(date);
        log.info("currentStr={}", currentStr);
    }
}
