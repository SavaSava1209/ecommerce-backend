package com.usc.http;

public class ProductDto {
	private String name;
	private String sku;
	private long category;
	private String description;
	private String imageUrl;
	private boolean active;
	private int unitsInStock;
	private int unitPrice;
	
	
	


	public int getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}


	public ProductDto(String name, String sku, long category, String description, String imageUrl, boolean active,
			int unitsInStock, int unitPrice) {
		super();
		this.name = name;
		this.sku = sku;
		this.category = category;
		this.description = description;
		this.imageUrl = imageUrl;
		this.active = active;
		this.unitsInStock = unitsInStock;
		this.unitPrice = unitPrice;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSku() {
		return sku;
	}


	public void setSku(String sku) {
		this.sku = sku;
	}


	public long getCategory() {
		return category;
	}


	public void setCategory(long category) {
		this.category = category;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public int getUnitsInStock() {
		return unitsInStock;
	}


	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}
	
	

}
