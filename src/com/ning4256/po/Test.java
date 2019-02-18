package com.ning4256.po;

import java.util.ArrayList;
import java.util.Random;

public class Test {
	public static void main(String[] args) {
		for (int  i = 0;  i < 1000000;  i++) {
			Random random = new Random();
			int num = random.nextInt();
			ArrayList<Integer> list = new ArrayList<>();
			for (Integer j : list) {
				if(j != num) {
					list.add(num);
				}
			}
		}
	
	}
}
