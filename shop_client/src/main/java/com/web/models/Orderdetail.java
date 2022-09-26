package com.web.models;

import java.util.Date;

public class Orderdetail {

	private int odtId;
	private Date odtCreateAt;
	private byte odtDeleted;
	private int odtPrice;
	private int odtQuatity;
	private String odtSize;
	private int odtTotal;
	private Date odtUpdateAt;
	private Order order;
	private Product product;

	public Orderdetail() {
	}

	public int getOdtId() {
		return this.odtId;
	}

	public void setOdtId(int odtId) {
		this.odtId = odtId;
	}

	public Date getOdtCreateAt() {
		return this.odtCreateAt;
	}

	public void setOdtCreateAt(Date odtCreateAt) {
		this.odtCreateAt = odtCreateAt;
	}

	public byte getOdtDeleted() {
		return this.odtDeleted;
	}

	public void setOdtDeleted(byte odtDeleted) {
		this.odtDeleted = odtDeleted;
	}

	
	public String getOdtSize() {
		return this.odtSize;
	}

	public void setOdtSize(String odtSize) {
		this.odtSize = odtSize;
	}


	public Date getOdtUpdateAt() {
		return this.odtUpdateAt;
	}

	public void setOdtUpdateAt(Date odtUpdateAt) {
		this.odtUpdateAt = odtUpdateAt;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getOdtPrice() {
		return odtPrice;
	}

	public void setOdtPrice(int odtPrice) {
		this.odtPrice = odtPrice;
	}

	public int getOdtQuatity() {
		return odtQuatity;
	}

	public void setOdtQuatity(int odtQuatity) {
		this.odtQuatity = odtQuatity;
	}

	public int getOdtTotal() {
		return odtTotal;
	}

	public void setOdtTotal(int odtTotal) {
		this.odtTotal = odtTotal;
	}

}