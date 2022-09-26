package com.web.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the comment database table.
 * 
 */
@Entity
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="com_id")
	private int comId;

	@Column(name="com_content")
	private String comContent;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="com_create_at")
	private Date comCreateAt;

	@Column(name="com_deleted")
	private byte comDeleted;

	@Column(name="com_name")
	private int comName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="com_update_at")
	private Date comUpdateAt;

	//bi-directional many-to-one association to Product
	@ManyToOne
	
	@JoinColumn(name="pro_id")
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