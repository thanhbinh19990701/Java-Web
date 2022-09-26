package com.web.models;

import java.util.Date;

public class Customer{

	private int cusId;
	private String cusAddress;
	private Date cusCreateAt;
	private byte cusDeleted;
	private String cusEmail;
	private String cusImage;
	private String cusName;
	private String cusPassword;
	private int cusPhone;
	private byte cusStatus;
	private Date cusUpdateAt;
	public Customer() {
	}

	public int getCusId() {
		return this.cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public String getCusAddress() {
		return this.cusAddress;
	}

	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}

	public Date getCusCreateAt() {
		return this.cusCreateAt;
	}

	public void setCusCreateAt(Date cusCreateAt) {
		this.cusCreateAt = cusCreateAt;
	}

	public byte getCusDeleted() {
		return this.cusDeleted;
	}

	public void setCusDeleted(byte cusDeleted) {
		this.cusDeleted = cusDeleted;
	}

	public String getCusEmail() {
		return this.cusEmail;
	}

	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}

	public String getCusImage() {
		return this.cusImage;
	}

	public void setCusImage(String cusImage) {
		this.cusImage = cusImage;
	}

	public String getCusName() {
		return this.cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusPassword() {
		return this.cusPassword;
	}

	public void setCusPassword(String cusPassword) {
		this.cusPassword = cusPassword;
	}

	public int getCusPhone() {
		return this.cusPhone;
	}

	public void setCusPhone(int cusPhone) {
		this.cusPhone = cusPhone;
	}

	public byte getCusStatus() {
		return this.cusStatus;
	}

	public void setCusStatus(byte cusStatus) {
		this.cusStatus = cusStatus;
	}

	public Date getCusUpdateAt() {
		return this.cusUpdateAt;
	}

	public void setCusUpdateAt(Date cusUpdateAt) {
		this.cusUpdateAt = cusUpdateAt;
	}

}