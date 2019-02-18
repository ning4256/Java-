package com.ning4256.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.ning4256.annotation.Annonation.Column;
import com.ning4256.annotation.Annonation.Table;
import com.ning4256.enumdemo.Person;

public class SqlTest {
	public static void main(String[] args) throws Exception {
		Person p = new Person();
		p.setName("wanglu");
		p.setAge(25);
		String querySQL = null;
		try {
			querySQL = query(p);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		System.out.println(querySQL);
	}

	private static String query(Object p) throws Exception {
		StringBuilder str = new StringBuilder();
		// 通过反射获取Class对象，以便获取注解的值
		Class<?> obj = p.getClass();
		// 判断该对象的类上有没有注解@Table
		boolean isExistsTable = obj.isAnnotationPresent(Table.class);
		System.out.println(isExistsTable);
		if (!isExistsTable) {
			return null;
		}
		// 获取Table注解，并获取注解的值，即表名
		Table table = (Table) obj.getAnnotation( Table.class);
		String tableName = table.value();
		// 拼装sql
		str.append("select * from ").append(tableName).append(" where 1=1 ");
		// 获取所有的成员变量，并遍历出来成员变量上的注解值
		Field[] fields = obj.getDeclaredFields();
		for (Field field : fields) {
			Boolean isExistColumn = field.isAnnotationPresent((Class<? extends Annotation>) Column.class);
			if (!isExistColumn) {
				continue;
			}
			// 获取成员变量上的注解值
			Column column = field.getAnnotation( Column.class);
			String columnName = column.value();
			// 获取成员变量的get方法名
			String methodName = "get" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
			
			// 获取成员变量的get方法
			Method method = obj.getMethod(methodName);
			
			// 执行成员变量的get方法，参数为该对象
			Object value = method.invoke(p);
			System.out.println(value);
			// 过滤掉成员变量中的null值，以及0
			if (null == value || (value instanceof Integer && (Integer) value == 0)) {
				continue;
			}
			str.append(" and ").append(columnName).append("=").append(value);
		}

		return str.toString();
	}
}
