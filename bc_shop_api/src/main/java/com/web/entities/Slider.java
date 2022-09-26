package com.web.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the slider database table.
 * 
 */
@Entity
@NamedQuery(name="Slider.findAll", query="SELECT s FROM Slider s")
public class Slider implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="sli_id")
	private int sliId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="sli_create_at")
	private Date sliCreateAt;

	@Column(name="sli_deleted")
	private byte sliDeleted;

	@Column(name="sli_image")
	private String sliImage;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="sli_update_at")
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