package com.yq.demo.common;

public enum StepContOnFailEnum {
    //1.，not continue when previous step failed， 2，continue，
	 NO("No", 1), YES("Yes", 2);
	 
    private String name;
    private int index;

     // 构造方法
    private StepContOnFailEnum(String name, int index) {
         this.name = name;
         this.index = index;
    }

     // 普通方法
    public static String getNameByIndex(int index) {
         for (StepContOnFailEnum c : StepContOnFailEnum.values()) {
             if (c.getIndex() == index) {
                 return c.name;
             }
         }
         return null;
    }

 	public static StepContOnFailEnum getByIndex(int index) {
		for (StepContOnFailEnum c : StepContOnFailEnum.values()) {
			if (c.getIndex() == index) {
				return c;
			}
		}
		return null;
	}

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }
}
