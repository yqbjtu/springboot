package com.yq.demo.common;

public enum StepStatusEnum {
    // 
	 NONE("None", 1), WAITING("Waiting", 2), EXECUTING("Running", 3),PASS("Pass", 4),
	 FAILED("Failed", 5), WARNING("Warning",6);

     private String name;
     private int index;

     // 构造方法
     private StepStatusEnum(String name, int index) {
         this.name = name;
         this.index = index;
     }

     // 普通方法
     public static String getNameByIndex(int index) {
         for (StepStatusEnum c : StepStatusEnum.values()) {
             if (c.getIndex() == index) {
                 return c.name;
             }
         }
         return null;
     }

 	public static StepStatusEnum getByIndex(int index) {
		for (StepStatusEnum c : StepStatusEnum.values()) {
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
