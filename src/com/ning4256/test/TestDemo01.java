package com.ning4256.test;

import java.util.Random;

public class TestDemo01 {
	public static void main(String[] args) {
		int [] numbers = new int[1000000];
		for(int i = 0; i < numbers.length; i++) {
			numbers[i] = i + 1;
		}
		int len = numbers.length;
		int  index = -1;
		Random random = new Random();
		for(int i = 0; i < len; ) {
			index = random.nextInt(len);
			
			System.out.println(numbers[index]);
//			System.out.println("len:" + len);
			numbers[index] = numbers[len - 1];
			len--;
		}
	
	}
}
