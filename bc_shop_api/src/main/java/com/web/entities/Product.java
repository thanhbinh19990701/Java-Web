package com.web.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pro_id")
	private int proId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="pro_create_at")
	private Date proCreateAt;

	@Column(name="pro_deleted")
	private byte proDeleted;

	@Column(name="pro_describes")
	private String proDescribes;

	@Column(name="pro_image")
	private String proImage;

	@Column(name="pro_name")
	private String proName;

	@Column(name="pro_price")
	private int proPrice;

	@Column(name="pro_quatity")
	private int proQuatity;

	@Column(name="pro_sale")
	private int proSale;

	@Column(name="pro_size")
	private String proSize;

	@Column(name="pro_sold")
	private int proSold;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="pro_update_at")
	private Date proUpdateAt;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="product")
	@JsonIgnore
	private List<Comment> comments;

	//bi-directional many-to-one association to Orderdetail
	@OneToMany(mappedBy="product")
//	@JsonBackReference
	@JsonIgnore
	private List<Orderdetail> orderdetails;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="cat_id")
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

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setProduct(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setProduct(null);

		return comment;
	}

	public List<Orderdetail> getOrderdetails() {
		return this.orderdetails;
	}

	public void setOrderdetails(List<Orderdetail> orderdetails) {
		this.orderdetails = orderdetails;
	}

	public Orderdetail addOrderdetail(Orderdetail orderdetail) {
		getOrderdetails().add(orderdetail);
		orderdetail.setProduct(this);

		return orderdetail;
	}

	public Orderdetail removeOrderdetail(Orderdetail orderdetail) {
		getOrderdetails().remove(orderdetail);
		orderdetail.setProduct(null);

		return orderdetail;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}