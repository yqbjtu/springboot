package com.yq;

/**
 * Simple to Introduction
 * className: MainDemo
 *
 * @author EricYang
 * @version 2018/11/1 12:54
 */

import static spark.Spark.*;

public class HelloWorldSpark {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
    }
}