package com.ning4256.annotation;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ning4256.annotation.Annonation.Column;
import com.ning4256.annotation.Annonation.Table;
import com.ning4256.po.CategoryPo;

public class CRUD_final {
	public static void main(String[] args) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("category_id", 2);
		param.put("category_name", "大米");
		System.out.println(find(param, CategoryPo.class));
	}
	
	
	public static <T> List<T> find(Map<String,Object> param,Class<T> cl) throws Exception{
		List<T> list=null;
		List<Object> values=new ArrayList<Object>();
		//完成SQL拼接
		String sql=getSQL(param,cl,values);
		System.out.println(sql+":"+values);
		//执行SQL
		//获取连接对象
		Connection con=JDBCUtil.getConnection();
		//创建状态
		PreparedStatement state=null;
		ResultSet set=null;
		try {
			state=con.prepareStatement(sql);
			//设置参数
			setParam(state, values);
			//执行SQL
			set=state.executeQuery();
			//处理结果集
			list=handleResultSet(set, cl);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(con, state, set);
		}
		return list;
	}
	public static <T> List<T> handleResultSet(ResultSet set,Class<T> cl) throws Exception{
		List<T> list=new ArrayList<T>();
		while(set.next()){
			//创建目标PO
			T po=cl.newInstance();
			//获取目标PO的本类所有属性
			Field[] fields=cl.getDeclaredFields();
			for (Field field : fields) {
				//判断该属性是否存在Column注解
				if(!field.isAnnotationPresent(Column.class)){
					continue;
				}
				//获取Column注解
				Column column=field.getDeclaredAnnotation(Column.class);
				//获取列名
				String columnName=column.value();
				//根据列名取值
				Object value=set.getObject(columnName);
				//突破访问权限
				field.setAccessible(true);
				//赋值
				field.set(po, value);
			}
			list.add(po);
		}
		return list;
	}
	public static void setParam(PreparedStatement state,List<Object> values) throws Exception{
		for (int i = 0; i < values.size(); i++) {
			state.setObject(i+1, values.get(i));
		}
	}
	public static <T> String getSQL(Map<String,Object> param,Class<T> cl,List<Object> values){
		StringBuilder sb=new StringBuilder();
		//判断是否存在Table注解
		if(!cl.isAnnotationPresent(Table.class)){
			return null;
		}
		//获取Table注解
		Table table=cl.getDeclaredAnnotation(Table.class);
		//获取表名
		String tableName=table.value();
		if(tableName == null || tableName.length() < 1){
			return null;
		}
		sb.append("SELECT * FROM "+tableName);
		//获取map所有键
		Set<String> set=param.keySet();
		//存where的使用状态
		boolean whereState=false;
		for (String key : set) {
			if(whereState){
				sb.append(" AND `"+key+"`=?");
			}else{
				sb.append(" WHERE `"+key+"`=?");
				whereState=true;
			}
			values.add(param.get(key));
		}
		return sb.toString();
	}
}
