package com.web.models;

import java.util.Date;


public class Slider  {

	private int sliId;
	private Date sliCreateAt;
	private byte sliDeleted;
	private String sliImage;
	private Date sliUpdateAt;

	public Slider() {
	}

	public int getSliId() {
		return this.sliId;
	}

	public void setSliId(int sliId) {
		this.sliId = sliId;
	}

	public Date getSliCreateAt() {
		return this.sliCreateAt;
	}

	public void setSliCreateAt(Date sliCreateAt) {
		this.sliCreateAt = sliCreateAt;
	}

	public byte getSliDeleted() {
		return this.sliDeleted;
	}

	public void setSliDeleted(byte sliDeleted) {
		this.sliDeleted = sliDeleted;
	}

	public String getSliImage() {
		return this.sliImage;
	}

	public void setSliImage(String sliImage) {
		this.sliImage = sliImage;
	}

	public Date getSliUpdateAt() {
		return this.sliUpdateAt;
	}

	public void setSliUpdateAt(Date sliUpdateAt) {
		this.sliUpdateAt = sliUpdateAt;
	}

}