package com.web.models;

public class CartItem {
	private Product product;
	private int quatity;
	private String size;
	public CartItem() {
		
	}
	public CartItem(Product product, int quatity, String size) {
		super();
		this.product = product;
		this.quatity = quatity;
		this.size = size;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuatity() {
		return quatity;
	}
	public void setQuatity(int quatity) {
		this.quatity = quatity;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}

	
	
}
