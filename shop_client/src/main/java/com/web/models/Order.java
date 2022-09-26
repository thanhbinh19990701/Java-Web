package com.web.models;

import java.util.Date;


public class Order {
	
	private int ordId;
	private Date ordCreateAt;
	private byte ordDeleted;
	private int ordStastus;
	private Date ordUpdateAt;
	private int cusId;

	public int getcusId() {
		return cusId;
	}

	public void setcusId(int cusId) {
		this.cusId = cusId;
	}

	public Order() {
	}

	public int getOrdId() {
		return this.ordId;
	}

	public void setOrdId(int ordId) {
		this.ordId = ordId;
	}

	public Date getOrdCreateAt() {
		return this.ordCreateAt;
	}

	public void setOrdCreateAt(Date ordCreateAt) {
		this.ordCreateAt = ordCreateAt;
	}

	public byte getOrdDeleted() {
		return this.ordDeleted;
	}

	public void setOrdDeleted(byte ordDeleted) {
		this.ordDeleted = ordDeleted;
	}

	public int getOrdStastus() {
		return this.ordStastus;
	}

	public void setOrdStastus(int ordStastus) {
		this.ordStastus = ordStastus;
	}

	public Date getOrdUpdateAt() {
		return this.ordUpdateAt;
	}

	public void setOrdUpdateAt(Date ordUpdateAt) {
		this.ordUpdateAt = ordUpdateAt;
	}


	

}