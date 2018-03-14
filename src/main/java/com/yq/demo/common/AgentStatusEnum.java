package com.yq.demo.common;

public enum AgentStatusEnum {
    //
    ONLINE("Online", 1), OFFLINE("Offline", 0);

     private String name;
     private int index;

     // 构造方法
     private AgentStatusEnum(String name, int index) {
         this.name = name;
         this.index = index;
     }

     // 普通方法
     public static String getNameByIndex(int index) {
         for (AgentStatusEnum c : AgentStatusEnum.values()) {
             if (c.getIndex() == index) {
                 return c.name;
             }
         }
         return null;
     }

     public static AgentStatusEnum getByIndex(int index) {
        for (AgentStatusEnum c : AgentStatusEnum.values()) {
            if (c.getIndex() == index) {
                return c;
            }
        }
        return null;
    }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public int getIndex() {
         return index;
     }

     public void setIndex(int index) {
         this.index = index;
     }
}
