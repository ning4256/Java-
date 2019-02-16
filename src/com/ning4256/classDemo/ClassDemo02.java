package com.ning4256.classDemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import com.ning4256.po.UserPo;

public class ClassDemo02 {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException {
		try {
			@SuppressWarnings("unchecked")
			Class<UserPo> class1 = (Class<UserPo>) Class.forName("com.ning4256.po.UserPo");
			Constructor<UserPo> c1 = class1.getConstructor(int.class, String.class);
			UserPo userPo = c1.newInstance(1, "伍川");
			System.out.println(userPo.toString());

			// 无参构造
			UserPo userPo1 = class1.newInstance();
			userPo1.setId(2);
			userPo1.setName("伍川");
			System.out.println(userPo1);
			
			StringBuilder sb = new StringBuilder();
			sb.append(Modifier.toString(c1.getModifiers()) + " class ");
			sb.append(class1.getSimpleName());
			System.out.println(sb);
		
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
