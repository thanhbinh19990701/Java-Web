package com.web.models;


import java.util.Date;



public class Category{
	private int catId;
	private Date catCreateAt;
	private byte catDeleted;
	private String catName;
	private Date catUpdateAt;
	public Category() {
	}

	public Date getCatCreateAt() {
		return this.catCreateAt;
	}

	public void setCatCreateAt(Date catCreateAt) {
		this.catCreateAt = catCreateAt;
	}

	public byte getCatDeleted() {
		return this.catDeleted;
	}

	public void setCatDeleted(byte catDeleted) {
		this.catDeleted = catDeleted;
	}

	public int getCatId() {
		return this.catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return this.catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public Date getCatUpdateAt() {
		return this.catUpdateAt;
	}

	public void setCatUpdateAt(Date catUpdateAt) {
		this.catUpdateAt = catUpdateAt;
	}


}