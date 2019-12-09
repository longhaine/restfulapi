package com.restful.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "gender")
	private String gender;
	
	@ManyToOne
	@JoinColumn(name = "id_brands")
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name = "id_categories")
	private Category category;
	
	public Product() {
		
	}
	public Product(int id, String name, float price, String image, String gender) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.image = image;
		this.gender = gender;
	}
	public Product(String name, float price, String image, String gender) {
		this.name = name;
		this.price = price;
		this.image = image;
		this.gender = gender;
	}
	

	public float getPrice() {
		return price;
	}
	public String getImage() {
		return image;
	}
	public String getGender() {
		return gender;
	}


	public void setPrice(float price) {
		this.price = price;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
}
