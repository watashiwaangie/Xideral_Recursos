package com.springdemo.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String Name;
	
	@Column(name="universal_code")
	private String UCode;
	
	@Column(name="price")
	private String Price;
	
	@Column(name="description")
	private String Description;

	public Product() {

	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return Name;
	}



	public void setName(String name) {
		Name = name;
	}



	public String getUCode() {
		return UCode;
	}



	public void setUCode(String uCode) {
		UCode = uCode;
	}



	public String getPrice() {
		return Price;
	}



	public void setPrice(String price) {
		Price = price;
	}
	
	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", Name=" + Name + ", UCode=" + UCode + ", Price=" + Price + ", Description="
				+ Description + "]";
	}

		
}





