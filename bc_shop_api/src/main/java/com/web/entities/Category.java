package com.web.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cat_id")
	private int catId;

	/**
	 * Interface Temporal mô phỏng một khái niệm chung về ngày tháng (date), 
	 * thời gian (time) và độ lệch thời gian (time-offset).
	 * Nó cung cấp các phương thức cơ bản để để lấy được các thông tin từ các đối tượng .
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="cat_create_at")
	private Date catCreateAt;

	@Column(name="cat_deleted")
	private byte catDeleted;


	@Column(name="cat_name")
	private String catName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="cat_update_at")
	private Date catUpdateAt;

	//bi-directional many-to-one association to Product
	/**
	 * @JsonIgnore là 1 class-level anntation dùng để đánh dấu các thuộc tính 	
	 * sẽ bị Jackson bỏ qua trong serialization và deserialization.
	 */
	
	@OneToMany(mappedBy="category")

	@JsonBackReference
	private List<Product> products;

	public Category() {
	}

	public Date getCatCreateAt() {
		return this.catCreateAt;
	}

	public void setCatCreateAt(Date catCreateAt) {
		this.catCreateAt = catCreateAt;
	}

	public byte getCatDeleted() {
		return this.catDeleted;
	}

	public void setCatDeleted(byte catDeleted) {
		this.catDeleted = catDeleted;
	}

	public int getCatId() {
		return this.catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return this.catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public Date getCatUpdateAt() {
		return this.catUpdateAt;
	}

	public void setCatUpdateAt(Date catUpdateAt) {
		this.catUpdateAt = catUpdateAt;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setCategory(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setCategory(null);

		return product;
	}

}