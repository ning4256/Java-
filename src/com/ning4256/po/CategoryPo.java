package com.ning4256.po;

import com.ning4256.annotation.Annonation.Column;
import com.ning4256.annotation.Annonation.Table;

@Table("category")
public class CategoryPo {
	@Column(value = "category_id")
	private int category_id;
	@Column(value = "category_name")
	private String category_name;
	@Column(value = "category_pic")
	private String category_pic;
	@Column(value = "category_description")
	private String category_description;
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getCategory_pic() {
		return category_pic;
	}
	public void setCategory_pic(String category_pic) {
		this.category_pic = category_pic;
	}
	public String getCategory_description() {
		return category_description;
	}
	public void setCategory_description(String category_description) {
		this.category_description = category_description;
	}
	@Override
	public String toString() {
		return "Category [category_id=" + category_id + ", category_name=" + category_name + ", category_pic="
				+ category_pic + ", category_description=" + category_description + "]";
	}
	
	
}
