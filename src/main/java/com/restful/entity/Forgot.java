package com.restful.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "forgot")
public class Forgot {
	
	@OneToOne
	@JoinColumn(name="email")
	private Account account;
	
	@Id
	private String pathinfo;
	
	@Column(name ="date")
	private String date;
	
	public Forgot() {
		
	}
	public Forgot(Account account) {
		this.account = account;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		date = format.format(new Date());
		this.pathinfo = account.getEmail()+date+"forgot";
		this.date = date;
	}
	public String getDate() {
		return date;
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
	public String getPathinfo() {
		return pathinfo;
	}
	public void setPathinfo(String pathinfo) {
		this.pathinfo = pathinfo;
	}
	
}
