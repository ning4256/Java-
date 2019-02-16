package com.ning4256.classDemo;

import java.lang.reflect.Field;

public class ClassDemo01 {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		//方式一
		try {
			// 类加载，不做初始化
			Class<?> class1 = Class.forName("com.ning4256.classDemo.ClassDemo01");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//方式二
		//类加载，初始化只做静态加载
		Class<String> class2 = String.class;

		System.out.println(class2.newInstance());
	}
}

class classDemo {
	public static String name = "classDemo";
	static {
		System.out.println("classDemo静态代码块");
	}

	public classDemo() {
		System.out.println("classDemo的构造器");
	}
}