package com.ning4256.enumdemo;

public enum Sex {
	male("男"), female("女");
	private String sexName;
	private Sex(String sexName) {
		this.sexName = sexName;
	}
	public String getSexName() {
		return sexName;
	}
	public void setSexName(String sexName) {
		this.sexName = sexName;
	}
	
}
