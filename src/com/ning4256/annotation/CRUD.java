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

public class CRUD {
	public static void main(String[] args) {
		Map<String, Object> parm = new HashMap<>();
		parm.put("category_id", 5);
		parm.put("category_name", "小米");
		System.out.println(find(parm, CategoryPo.class));
	}

	private static <T> List<T> find(Map<String, Object> parm, Class<T> cl) {
		List<T> list = new ArrayList<>();
		List<Object> values = new ArrayList<>();
		//完成sql拼接
		String sql = getSQL(parm, cl,values);
		System.out.println(sql + "  || " + values);
		return list;
	}

	
	//获取sql
	public static <T> String getSQL(Map<String, Object> param, Class<T> cl, List<Object> values) {
		StringBuilder sb = new StringBuilder();
		//判断是否有table注解
		if(!cl.isAnnotationPresent(Table.class)){
			return null;
		}
		//获取table注解
		Table table = cl.getAnnotation(Table.class);
		//获取table名字
		String tableName = table.value();
		if(tableName == null || tableName.length() < 1) {
			return null;
		}
		sb.append("SELECT * FROM " +tableName);
		//获取map的键
		Set<String> set = param.keySet();
		//key的使用状态
		boolean whereState = false;
		for (String key : set) {
			if(whereState) {
				sb.append(" AND `" + key + "`=?");
			}else {
				sb.append(" WHERE `" + key + "`=?");
				whereState = true;
			}
			values.add(param.get(key));
		}
		
		
		return sb.toString();
	}

	// 获取sql查询的po
	private static <T> List<T> SelectPo(String sql, Class<?> cl, Object[] param)
			throws SQLException, InstantiationException, IllegalAccessException {
		List<T> list = new ArrayList<T>();
		// 进行数据检查
		if (sql == null || cl == null || param == null) {
			return list;
		}
		// 获取数据库连接
		Connection con = JDBCUtil.getConnection();
		// 创建状态
		PreparedStatement state = null;
		ResultSet set = null;
		try {
			state = con.prepareStatement(sql);
			// 设置参数
			for (int i = 0; i < param.length; i++) {
				state.setObject(i + 1, param[i]);
			}
			// 执行SQL
			set = state.executeQuery();
			// 处理结果集
			while (set.next()) {
				// 循环处理每一行数据
				// 创建目标PO对象
				T po = (T) cl.newInstance();
				// 获取目标类本类的所有属性
				Field[] fields = cl.getDeclaredFields();
				for (Field field : fields) {
					// 判断该属性上是否存在Column注解
					if (!field.isAnnotationPresent(Column.class)) {
						continue;
					}
					// 获取Column注解
					Column column = field.getDeclaredAnnotation(Column.class);
					// 获取属性对应的列名
					String columnName = column.value();
					if (columnName == null || columnName.length() < 1) {
						continue;
					}
					Object value = null;
					try {
						// 根据列名取出查询结果
						value = set.getObject(columnName);

					} catch (Exception e) {
						continue;
					}
					// 突破访问权限
					field.setAccessible(true);
					// 将查询的列值存到PO中
					field.set(po, value);

				}
				list.add(po);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, state, set);
		}
		return list;
	}

	// 获取sql查询语句
	public static <T> String executeSQL(T po) throws Exception {
		StringBuilder sb = new StringBuilder();
		// 获取目标Class实例
		Class<?> cl = po.getClass();
		// 判断类上是否存在Table注解
		if (!cl.isAnnotationPresent(Table.class)) {
			return "";
		}
		// 获取Table注解
		Table table = cl.getDeclaredAnnotation(Table.class);
		// 获取表名
		String tableName = table.value();
		// 表名的SQL拼接
		sb.append("SELECT * FROM " + tableName);
		// SELECT * FROM 表名 WHERE 列1=值1 AND 列2=值3
		// 哪些列的值会作为查询条件
		// 怎么判断属性被赋了值
		// 看属性是否为默认值
		/*
		 * 基本数据类型 数字 0 非数字 char boolean 引用数据类型 null
		 * 
		 * 数字和字符串
		 */
		// where and确定
		// 获取目标类所有属性
		Field[] fields = cl.getDeclaredFields();
		// 存where是否被使用
		boolean whereState = false;
		for (Field field : fields) {
			// 判断当前属性上是否存在Column注解
			if (!field.isAnnotationPresent(Column.class)) {
				continue;
			}
			// 获取Column注解
			Column column = field.getDeclaredAnnotation(Column.class);
			String columnName = column.value();
			if (columnName == null || columnName.length() < 1) {
				continue;
			}
			// 突破访问权限
			field.setAccessible(true);
			// 获取属性数值
			Object obj = field.get(po);
			if (obj == null) {
				continue;
			} else if (obj instanceof Number) {
				// 数字型
				Number num = (Number) obj;
				if (num.doubleValue() == 0) {
					continue;
				}
				// SQL拼接
				if (whereState) {
					// and
					sb.append(" AND `" + columnName + "`=" + num);
				} else {
					// where
					sb.append(" WHERE `" + columnName + "`=" + num);
					whereState = true;
				}
			} else if (obj instanceof String) {
				// SQL拼接
				if (whereState) {
					// and
					sb.append(" AND `" + columnName + "`='" + obj + "'");
				} else {
					// where
					sb.append(" WHERE `" + columnName + "`='" + obj + "'");
					whereState = true;
				}
			}
		}
		sb.append(";");
		return sb.toString();
	}
}
