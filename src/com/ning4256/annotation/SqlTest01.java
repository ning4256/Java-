package com.ning4256.annotation;

import java.lang.reflect.Field;

import com.ning4256.annotation.Annonation.Column;
import com.ning4256.annotation.Annonation.Table;
import com.ning4256.po.UserPo;

public class SqlTest01 {
	public static void main(String[] args) throws Exception {
		UserPo userPo = new UserPo();
		userPo.setId(11);
		System.out.println(executeSQL(userPo));
	}
	public static <T> String executeSQL(T po) throws Exception{
		StringBuilder sb=new StringBuilder();
		//获取目标Class实例
		Class<?> cl=po.getClass();
		//判断类上是否存在Table注解
		if(!cl.isAnnotationPresent(Table.class)){
			return "";
		}
		//获取Table注解
		Table table=cl.getDeclaredAnnotation(Table.class);
		//获取表名
		String tableName=table.value();
		//表名的SQL拼接
		sb.append("SELECT * FROM "+tableName);
		//SELECT * FROM 表名 WHERE 列1=值1 AND 列2=值3
		//哪些列的值会作为查询条件
		//怎么判断属性被赋了值
		//看属性是否为默认值
		/*
		 * 基本数据类型
		 * 	数字 0
		 *  非数字 char boolean
		 * 引用数据类型
		 * 	null
		 * 
		 * 数字和字符串
		 */
		//where and确定
		//获取目标类所有属性
		Field[] fields=cl.getDeclaredFields();
		//存where是否被使用
		boolean whereState=false;
		for (Field field : fields) {
			//判断当前属性上是否存在Column注解
			if(!field.isAnnotationPresent(Column.class)){
				continue;
			}
			//获取Column注解
			Column column=field.getDeclaredAnnotation(Column.class);
			String columnName=column.value();
			if(columnName == null || columnName.length() < 1){
				continue;
			}
			//突破访问权限
			field.setAccessible(true);
			//获取属性数值
			Object obj=field.get(po);
			if(obj == null){
				continue;
			}else if(obj instanceof Number){
				//数字型
				Number num=(Number) obj;
				if(num.doubleValue() == 0){
					continue;
				}
				//SQL拼接
				if(whereState){
					//and
					sb.append(" AND `"+columnName+"`="+num);
				}else{
					//where
					sb.append(" WHERE `"+columnName+"`="+num);
					whereState=true;
				}
			}else if(obj instanceof String){
				//SQL拼接
				if(whereState){
					//and
					sb.append(" AND `"+columnName+"`='"+obj+"'");
				}else{
					//where
					sb.append(" WHERE `"+columnName+"`='"+obj+"'");
					whereState=true;
				}
			}
		}
		sb.append(";");
		return sb.toString();
	}
	
}
