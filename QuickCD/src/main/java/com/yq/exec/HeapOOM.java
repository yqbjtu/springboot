package com.yq.exec;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple to Introduction
 * className: HeapOOM
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @author EricYang
 * @version 2019/2/24 22:28
 */
public class HeapOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while(true) {
            list.add(new OOMObject());
        }
    }
}
