package com.ning4256.classDemo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.ning4256.genritic.GeneriticDemo;
import com.ning4256.po.UserPo;

public class Test01 {
	public static <T> T enca(Map<?, ?> map, Class<T> class1) throws Exception {
		T t = null;
		// 非空判断
		if (map == null || class1 == null) {
			return t;
		}
		// 创建对象
		t = (T) class1.newInstance();
		// 操作属性
		// 获取本类中的所有属性
		Field[] fields = class1.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			fields[i].set(t, map.get(fields[i].getName()));
		}
		return t;
	}

	public static void main(String[] args) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("pass", 1234);
		map.put("id", 1);
		map.put("name", "伍川");
		
		@SuppressWarnings("unchecked")
		Class<UserPo> class1 = (Class<UserPo>) Class.forName("com.ning4256.po.UserPo");
		System.out.println(enca(map, class1));

		Method mainMethod = GeneriticDemo.class.getMethod("main", String[].class);
		mainMethod.invoke(null, (Object) new String[] { "a", "b", "c" });

	}
}
