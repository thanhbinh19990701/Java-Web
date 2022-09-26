package com.web.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ord_id")
	private int ordId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ord_create_at")
	private Date ordCreateAt;
	
	@Column(name="cus_id")
	private int cusId;
	
	
	@Column(name="ord_deleted")
	private byte ordDeleted;

	@Column(name="ord_stastus")
	private int ordStastus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ord_update_at")
	private Date ordUpdateAt;

	//bi-directional many-to-one association to Orderdetail
	@OneToMany(mappedBy="order")
	@JsonBackReference
	//@JsonIgnore
	private List<Orderdetail> orderdetails;

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

	public List<Orderdetail> getOrderdetails() {
		return this.orderdetails;
	}

	public void setOrderdetails(List<Orderdetail> orderdetails) {
		this.orderdetails = orderdetails;
	}

	public Orderdetail addOrderdetail(Orderdetail orderdetail) {
		getOrderdetails().add(orderdetail);
		orderdetail.setOrder(this);

		return orderdetail;
	}

	public Orderdetail removeOrderdetail(Orderdetail orderdetail) {
		getOrderdetails().remove(orderdetail);
		orderdetail.setOrder(null);

		return orderdetail;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	

	

}