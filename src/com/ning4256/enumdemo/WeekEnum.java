package com.ning4256.enumdemo;

public enum WeekEnum {
	MON("星期一", 1), TUE("星期二", 2), WEN("星期三", 3), THE("星期四", 4), FRI("星期五", 5), SAT("星期六", 6), SUN("星期七", 0);
	// 成员变量
	private String weekName;
	private int weekIndex;

	// 无参构造器
	private WeekEnum() {
	}

	// 有参构造器
	private WeekEnum(String weekName, int weekIndex) {
		this.weekName = weekName;
		this.weekIndex = weekIndex;
	}

	// 成员方法
	public String getWeekName() {
		return weekName;
	}

	public void setWeekName(String weekName) {
		this.weekName = weekName;
	}

	public int getWeekIndex() {
		return weekIndex;
	}

	public void setWeekIndex(int weekIndex) {
		this.weekIndex = weekIndex;
	}
	// 常用方法

	// 主方法
	public static void main(String[] args) {
		// 使用枚举实例
		WeekEnum mon = WeekEnum.MON;
		// compareTo方法
		int i = mon.compareTo(SAT);
		System.out.println(i);
		int j = mon.ordinal();
		System.out.println(j);
		int k = mon.hashCode();
		System.out.println(k);
		String name = mon.name();
		System.out.println(name);
		Class<WeekEnum> weekE = mon.getDeclaringClass();
		System.out.println(weekE);
		System.out.println(mon.getWeekName());

		// 获取枚举类的所有实例
		WeekEnum[] week = WeekEnum.values();
		for (WeekEnum weekEnum : week) {
			System.out.println(weekEnum.getWeekName());
		}
	}
}
