package com.ning4256.classDemo;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

import com.ning4256.po.UserPo;

public class ClassDemo02 {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		try {
			@SuppressWarnings("unchecked")
			Class<UserPo> class1 = (Class<UserPo>) Class.forName("com.ning4256.po.UserPo");
			Constructor<UserPo> c1 = class1.getConstructor(int.class, String.class);
			// UserPo userPo = c1.newInstance(1, "伍川");
			// System.out.println(userPo.toString());
			//
			// // 无参构造
			// UserPo userPo1 = class1.newInstance();
			// UserPo userPo2 = class1.getConstructor().newInstance();
			// userPo1.setId(2);
			// userPo1.setName("伍川");
			// System.out.println(userPo1);
			//
			// StringBuilder sb = new StringBuilder();
			// sb.append(Modifier.toString(c1.getModifiers()) + " class ");
			// sb.append(class1.getSimpleName());
			// System.out.println(sb);

			// //属性操作
			// Field[] fields = class1.getDeclaredFields();
			// for (Field field : fields) {
			// //修饰符
			// System.out.print(Modifier.toString(field.getModifiers()) + " ");
			// //类型
			// System.out.print(field.getType().getSimpleName() + " ");
			// //名称
			// System.out.println(field.getName() + ";" );
			// }
			//
			// //操作对象某属性
			// //调用无参构造类实例
			// UserPo userPo = class1.newInstance();
			// //获取指定的属性
			// Field field = class1.getDeclaredField("name");
			// field.setAccessible(true);
			// field.set(userPo, "admin");
			// System.out.println(field.get(userPo));

			// 操作方法
//			Method[] methods = class1.getMethods();
//			for (Method method : methods) {
//				// 修饰符
//				System.out.print(Modifier.toString(method.getModifiers()) + " ");
//				// 返回值
//				System.out.print(method.getReturnType().getSimpleName() + " ");
//				// 名称
//				System.out.print(method.getName() + "(");
//				// 参数列表
//				//获取参数类型
//				Class[] paClasses = method.getParameterTypes();
//				for (Class paClass : paClasses) {
//					System.out.print(paClass.getSimpleName() + " arg"); 
//				}
//				
//				System.out.println(")");
//			}
			//获取类对象
//			UserPo userPo = class1.newInstance();
//			//获取目标方法
//			Method method = class1.getMethod("setId", int.class);
//			//执行方法
//			method.invoke(userPo, 1);
//			Method method2 = class1.getDeclaredMethod("setId", int.class);
//			method2
			
			UserPo userPo = class1.newInstance();
			System.out.print(Modifier.toString(class1.getModifiers()) + " class ");
			
			System.out.println(class1.getSimpleName() + " {");
			//对象属性
			Field[] fields = class1.getDeclaredFields();
			for (Field field : fields) {
				System.out.print("  " + Modifier.toString(field.getModifiers()) + " ");
				System.out.print("" + field.getType().getSimpleName() + " ");
				System.out.println(field.getName());
			}
			//对象方法
			Method[] methods = class1.getDeclaredMethods();
			for (int i = 0; i < methods.length; i ++) {
				System.out.print("  "+ Modifier.toString(methods[i].getModifiers()) + " ");
				System.out.print(methods[i].getReturnType().getSimpleName() + " ");
				System.out.print(methods[i].getName() + "(");
				Class<?>[] parameters = methods[i].getParameterTypes();
				for (int j = 0; j < parameters.length; j++) {
					System.out.print(parameters[j].getSimpleName() + " arg" + j);
					if(j < parameters.length - 1) {
						System.out.print(",");
					}
					
				}
				System.out.print(")");
				System.out.println();
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
