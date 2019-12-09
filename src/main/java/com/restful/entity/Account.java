package com.restful.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name= "account")
public class Account {
	@Id
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "role")
	private int role;
	
	@Column(name = "verification")
	private int verification;
	
	public Account() {
		
	}
	
	public Account(String email, String password, String name, String address, String phone, int role, int verification) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.role = role;
		this.verification = verification;
	}
	
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
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
	public int getRole() {
		return role;
	}
	public int getVerification() {
		return verification;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public void setRole(int role) {
		this.role = role;
	}
	public void setVerification(int verification) {
		this.verification = verification;
	}
}
