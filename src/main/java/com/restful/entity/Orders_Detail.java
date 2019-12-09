package com.restful.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "orders_detail")
public class Orders_Detail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id_orders")
	private Order_Account order;
	
	@ManyToOne
	@JoinColumn(name="id_products")
	private Product product;
	
	public Orders_Detail() {
		
	}
	public Orders_Detail(Order_Account order, Product product) {
		this.order = order;
		this.product = product;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Order_Account getOrder() {
		return order;
	}

	public Product getProduct() {
		return product;
	}

	public void setOrder(Order_Account order) {
		this.order = order;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
