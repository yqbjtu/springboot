package com.yq.demo.common;

public enum BuildStatusEnum {
	// Build 状态，
	// 1， 表示create new， 2，starting, 表示将buildCallable放入了threadpool中，
	// 3， executing， build真正在threadpool中被执行了，也就是call方法被调用了
	// 4 complete
	NEW("Create New", 1), STARTING("Starting", 2), EXECUTING("Running", 3), COMPLETE("Complete", 4),
	// step 用running和下面这些
	NONE("None", 5), PASS("Pass", 6), FAILED("Failed", 7), WARNING("Warning", 8);
	// 成员变量

	private String name;
	private int index;

	// 构造方法
	private BuildStatusEnum(String name, int index) {
		this.name = name;
		this.index = index;
	}

	// 普通方法
	public static String getNameByIndex(int index) {
		for (BuildStatusEnum c : BuildStatusEnum.values()) {
			if (c.getIndex() == index) {
				return c.name;
			}
		}
		return null;
	}

	public static BuildStatusEnum getByIndex(int index) {
		for (BuildStatusEnum c : BuildStatusEnum.values()) {
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
