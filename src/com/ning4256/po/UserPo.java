package com.ning4256.po;

public class UserPo {
	private int id;
	private String name;
	public UserPo() {
		
	}
	
	public UserPo(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserPo [id=" + id + ", name=" + name + "]";
	}

	
}
