package com.restful.entity;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity(name = "verification")
public class Verification{

	@OneToOne
	@JoinColumn(name="email")
	private Account account;
	
	@Id
	private String pathinfo;
	
	@Column(name ="date")
	private String date;
	
	public Verification() {
		
	}
	public Verification(Account account) {
		this.account = account;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		date = format.format(new Date());
		this.pathinfo = account.getEmail()+date+"verified";
		this.date = date;
	}

	
	public String getPathInfo() {
		return pathinfo;
	}
	public String getDate() {
		return date;
	}
	public void setPathInfo(String pathInfo) {
		this.pathinfo = pathInfo;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
}
