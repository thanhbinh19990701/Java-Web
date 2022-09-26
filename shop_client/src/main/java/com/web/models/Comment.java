package com.web.models;

import java.util.Date;

public class Comment {
	
	private int comId;
	private String comContent;
	private Date comCreateAt;
	private byte comDeleted;
	private int comName;
	private Date comUpdateAt;
	private Product product;

	public Comment() {
	}

	public int getComId() {
		return this.comId;
	}

	public void setComId(int comId) {
		this.comId = comId;
	}

	public String getComContent() {
		return this.comContent;
	}

	public void setComContent(String comContent) {
		this.comContent = comContent;
	}

	public Date getComCreateAt() {
		return this.comCreateAt;
	}

	public void setComCreateAt(Date comCreateAt) {
		this.comCreateAt = comCreateAt;
	}

	public byte getComDeleted() {
		return this.comDeleted;
	}

	public void setComDeleted(byte comDeleted) {
		this.comDeleted = comDeleted;
	}

	public int getComName() {
		return this.comName;
	}

	public void setComName(int comName) {
		this.comName = comName;
	}

	public Date getComUpdateAt() {
		return this.comUpdateAt;
	}

	public void setComUpdateAt(Date comUpdateAt) {
		this.comUpdateAt = comUpdateAt;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}