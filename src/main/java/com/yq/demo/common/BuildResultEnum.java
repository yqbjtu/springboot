package com.yq.demo.common;

public enum BuildResultEnum {
	// 状态，1， 表示none， 2，pass， 3， failed， 4 warning
	NONE("None", 1), PASS("Pass", 2), FAILED("Failed", 3), WARNING("Warning", 4);

	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private BuildResultEnum(String name, int index) {
		this.name = name;
		this.index = index;
	}

	// 普通方法
	public static String getNameByIndex(int index) {
		for (BuildResultEnum c : BuildResultEnum.values()) {
			if (c.getIndex() == index) {
				return c.name;
			}
		}
		return null;
	}

	public static BuildResultEnum getByIndex(int index) {
		for (BuildResultEnum c : BuildResultEnum.values()) {
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
