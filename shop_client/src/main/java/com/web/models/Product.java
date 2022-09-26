package com.web.models;

import java.util.Date;


public class Product {
	
	private int proId;
	private Date proCreateAt;
	private byte proDeleted;
	private String proDescribes;
	private String proImage;
	private String proName;
	private int proPrice;
	private int proQuatity;
	private int proSale;
	private String proSize;
	private int proSold;
	private Date proUpdateAt;
	private Category category;

	public Product() {
	}

	public int getProId() {
		return this.proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public Date getProCreateAt() {
		return this.proCreateAt;
	}

	public void setProCreateAt(Date proCreateAt) {
		this.proCreateAt = proCreateAt;
	}

	public byte getProDeleted() {
		return this.proDeleted;
	}

	public void setProDeleted(byte proDeleted) {
		this.proDeleted = proDeleted;
	}

	public String getProDescribes() {
		return this.proDescribes;
	}

	public void setProDescribes(String proDescribes) {
		this.proDescribes = proDescribes;
	}

	public String getProImage() {
		return this.proImage;
	}

	public void setProImage(String proImage) {
		this.proImage = proImage;
	}

	public String getProName() {
		return this.proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public int getProPrice() {
		return this.proPrice;
	}

	public void setProPrice(int proPrice) {
		this.proPrice = proPrice;
	}

	public int getProQuatity() {
		return this.proQuatity;
	}

	public void setProQuatity(int proQuatity) {
		this.proQuatity = proQuatity;
	}

	public int getProSale() {
		return this.proSale;
	}

	public void setProSale(int proSale) {
		this.proSale = proSale;
	}

	public String getProSize() {
		return this.proSize;
	}

	public void setProSize(String proSize) {
		this.proSize = proSize;
	}

	public int getProSold() {
		return this.proSold;
	}

	public void setProSold(int proSold) {
		this.proSold = proSold;
	}

	public Date getProUpdateAt() {
		return this.proUpdateAt;
	}

	public void setProUpdateAt(Date proUpdateAt) {
		this.proUpdateAt = proUpdateAt;
	}


	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}