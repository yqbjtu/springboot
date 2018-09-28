package com.yq.demo.common;

public enum CmdTypeEnum {

    AgentTestConnReq("AgentTestConn", 0), AgentTestConnResp("AgentTestConnResp", 1),
    AgentInfoResp("AgentInfo", 2),
    StepCmd("StepCmd", 3),  StepResult("StepResult", 4),
    CleanupReq("Cleanup", 5), CleanupResp("Cleanup", 6);

     private String name;
     private int index;

     // 构造方法
     private CmdTypeEnum(String name, int index) {
         this.name = name;
         this.index = index;
     }

     // 普通方法
     public static String getNameByIndex(int index) {
         for (CmdTypeEnum c : CmdTypeEnum.values()) {
             if (c.getIndex() == index) {
                 return c.name;
             }
         }
         return null;
     }

     public static CmdTypeEnum getByIndex(int index) {
        for (CmdTypeEnum c : CmdTypeEnum.values()) {
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
