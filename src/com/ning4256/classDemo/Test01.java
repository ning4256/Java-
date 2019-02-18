package com.ning4256.classDemo;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.ning4256.po.UserPo;

public class Test01 {
	public static UserPo encap(Map<?, ?> map, Class<?> class1) throws Exception {
		UserPo userPo = (UserPo) class1.newInstance();
		Field[] fields = class1.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			fields[i].set(userPo, map.get(fields[i].getName()));
		}
		return userPo;
	}
	public static void main(String[] args) throws Exception {
		HashMap< String, Object> map = new HashMap<>();
		map.put("id", 2);
		Class<?> class1 = Class.forName("com.ning4256.po.UserPo");
		System.out.println(encap(map, class1));
	}
}
