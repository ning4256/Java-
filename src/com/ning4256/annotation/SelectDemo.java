package com.ning4256.annotation;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ning4256.annotation.Annonation.Column;
import com.ning4256.po.CategoryPo;

public class SelectDemo {
	public static void main(String[] args) throws Exception {
		String sql = "SELECT * FROM category WHERE category_id = ?";
		Object[] par = { 4 };
		System.out.println(SelectDemo(sql, CategoryPo.class, par));
	}

	private static <T> List<T> SelectDemo1(String sql, Class<CategoryPo> cl, Object[] param) throws SQLException, InstantiationException, IllegalAccessException {
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

	private static <T> List<T> SelectDemo(String sql, Class<?> class1, Object[] par) throws Exception {
		List<T> list = new ArrayList<T>();
		// 配置drivermannager参数url、username、password
		// 获取数据库连接
		if (sql == null || class1 == null || par.length < 1) {
			return list;
		}

		Connection con = JDBCUtil.getConnection();
		PreparedStatement state = null;
		ResultSet set = null;
		state = con.prepareStatement(sql);

		for (int i = 0; i < par.length; i++) {
			state.setObject(i + 1, par[i]);
		}

		// 执行sql
		set = state.executeQuery();
		while (set.next()) {
			// 处理每一行数据
			// 创建po对象
			T po = (T) class1.newInstance();
			// 获取目标本类的所有属性
			Field[] fields = class1.getDeclaredFields();
			for (Field field : fields) {
				// 判断该属性是否有column属性
				if (!field.isAnnotationPresent(Column.class)) {
					continue;
				}
				// 获取column注解
				Column column = field.getDeclaredAnnotation(Column.class);
				String columnName = column.value();
				if (columnName == null || columnName.length() < 1) {
					continue;
				}
				// 根据列名取出查询结果
				Object value = set.getObject(columnName);
				// 突破访问权限
				field.setAccessible(true);
				field.set(po, value);

			}
			list.add(po);

		}

		return list;
	}
}
