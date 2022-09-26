package com.web.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the orderdetail database table.
 * 
 */
@Entity
@NamedQuery(name="Orderdetail.findAll", query="SELECT o FROM Orderdetail o")
public class Orderdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="odt_id")
	private int odtId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="odt_create_at")
	private Date odtCreateAt;

	@Column(name="odt_deleted")
	private byte odtDeleted;

	@Column(name="odt_price")
	private String odtPrice;

	@Column(name="odt_quatity")
	private String odtQuatity;

	@Column(name="odt_size")
	private String odtSize;

	@Column(name="odt_total")
	private String odtTotal;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="odt_update_at")
	private Date odtUpdateAt;

	
	

	//bi-directional many-to-one association to Product
	@ManyToOne
//	@JsonIgnore
	@JoinColumn(name="pro_id")
	private Product product;

	

	//bi-directional many-to-one association to Order
	@ManyToOne
//	@JsonManagedReference
//	@JsonIgnore
	@JoinColumn(name="ord_id")
	private Order order;

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

	public String getOdtPrice() {
		return this.odtPrice;
	}

	public void setOdtPrice(String odtPrice) {
		this.odtPrice = odtPrice;
	}

	public String getOdtQuatity() {
		return this.odtQuatity;
	}

	public void setOdtQuatity(String odtQuatity) {
		this.odtQuatity = odtQuatity;
	}

	public String getOdtSize() {
		return this.odtSize;
	}

	public void setOdtSize(String odtSize) {
		this.odtSize = odtSize;
	}

	public String getOdtTotal() {
		return this.odtTotal;
	}

	public void setOdtTotal(String odtTotal) {
		this.odtTotal = odtTotal;
	}

	public Date getOdtUpdateAt() {
		return this.odtUpdateAt;
	}

	public void setOdtUpdateAt(Date odtUpdateAt) {
		this.odtUpdateAt = odtUpdateAt;
	}


	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order=order;
	}

}