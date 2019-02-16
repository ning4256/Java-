package com.ning4256.enumdemo;

public enum WeekEnum {
	MON, TUE, WEN, THE, FRI, SAT, SUN;
	public static void main(String[] args) {
		//使用枚举实例
		WeekEnum mon = WeekEnum.MON;
		System.out.println(mon);
		//获取枚举类的所有实例
		
		WeekEnum[] week = WeekEnum.values();
		for (WeekEnum weekEnum : week) {
			System.out.println(weekEnum);
		}
	}
}
