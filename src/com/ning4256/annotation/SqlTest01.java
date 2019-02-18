package com.ning4256.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.ning4256.annotation.Annonation.Table;
import com.ning4256.enumdemo.Person;

public class SqlTest01 {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Person p = new Person();
//		p.setName("伍川");
		p.setId(11);
		p.setAge(123);
//		p.setSex("男");
		System.out.println(query(p));
	}

	private static String query(Object object) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//字符串拼接
		StringBuilder sBuilder = new StringBuilder();
		//获取类加载
		Class<?> obj = object.getClass();
		//得到tableName
		Table table = obj.getAnnotation(Table.class);
		String tableName = table.value();
		//sql语句拼接
		sBuilder.append("select * from " + tableName + " where");
		//获取所有的属性
		Field[] fields = obj.getDeclaredFields();
		
		for (int i = 0; i < fields.length; i++) {
//			Column column = obj.getAnnotation(Column.class);
			
			String methodName = "get" + fields[i].getName().substring(0, 1).toUpperCase() + fields[i].getName().substring(1);
			Method method = obj.getMethod(methodName);
			Object value = method.invoke(object);
			
			if(value == null || (value instanceof Integer && (Integer) value == 0)) {
				continue;
			}
			sBuilder.append(" " + fields[i].getName() +"=" + value);
			if(i < fields.length  - 1) {
				sBuilder.append(" and");
			}
		}
			
		String[] str = sBuilder.toString().split(" ");
		StringBuilder sBuilder2 = new StringBuilder();
		for (int i = 0; i < str.length; i++) {
			System.out.println(str[i]);
			if(str[str.length - 1].equals("and") ) {
				str[str.length - 1] = "";
			}	
			sBuilder2.append(str[i] + " ");
		}
				
		return sBuilder2.toString();
	}
	
}
