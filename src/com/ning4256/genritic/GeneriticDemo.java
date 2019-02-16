package com.ning4256.genritic;

public class GeneriticDemo {
	public static <T extends Number> double add(T t, T k) {
		return t.doubleValue() + k.doubleValue();
	}
	public static void main(String[] args) {
		double result = add(1, 23);
		System.out.println(result);
	}
}
