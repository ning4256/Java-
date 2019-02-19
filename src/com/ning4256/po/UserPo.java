package com.ning4256.po;

import com.ning4256.annotation.Annonation.Column;
import com.ning4256.annotation.Annonation.Table;

@Table("person")
public class UserPo {
	@Column( "id")
	private int id;
	@Column("name")
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
