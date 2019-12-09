package com.restful.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "order_account")
public class Order_Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="email")
	private String email;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "idsession")
	private String idsession;
	
	public Order_Account() {
		
	}
	
	public Order_Account(int id,String date, float price) {
		this.id = id;
		this.date = date;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	public String getDate() {
		return date;
	}
	public float getPrice() {
		return price;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public String getPhone() {
		return phone;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdsession() {
		return idsession;
	}

	public void setIdsession(String idsession) {
		this.idsession = idsession;
	}
}
