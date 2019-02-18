package com.ning4256.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.TYPE;
 


public class Annonation {
//	@Override //重写
//	@Deprecated //过时
//	@SuppressWarnings //抑制编译器警告
	
//	
//
//	
//	@Retention(RetentionPolicy.RUNTIME)
//	@Target(ElementType.ANNOTATION_TYPE)
//	@Documented
//	@Inherited
//	public @interface test {
//		int age();
//		String name();
//	}
//	
//	
//	public void test () {
//	}
//	
////	@Table(value = "测试01")
//	
//	void test01() {
//		
//	}
//
//	
//	
//	@Retention(RetentionPolicy.RUNTIME)
//	@Target(ElementType.FIELD)
//	public @interface Table
//	{
//	    public String value();
//	}
//	
//	@Documented
//	@Retention(RetentionPolicy.RUNTIME)
//	@Target(ElementType.FIELD)
//	public @interface Column
//	{
//	    public String value();
//	}

	
	
	
	@Documented
	@Retention(RUNTIME)
	@Target(TYPE)
	public @interface Table
	{
	    public String value();
	}
	
	@Documented
	@Retention(RUNTIME)
	@Target(FIELD)
	public @interface Column
	{
	    public String value();
	}
	
	
	
	
	
	
	
//	标记annotation
//	元数据Annotation
}
