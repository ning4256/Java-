package com.ning4256.classDemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ClassDemo01 {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
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
//		Field[] fields = class2.getDeclaredFields();
//		for (Field field : fields) {
//			System.out.println(field.getName());
//		}
		Constructor<String> c1 = class2.getConstructor(String.class);
		
		String result = c1.newInstance("hah");
		System.out.println(result);
	}
}

