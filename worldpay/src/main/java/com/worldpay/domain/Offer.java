package com.worldpay.domain;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "OFFER")
@XmlRootElement
public class Offer {

	@Id
	private Integer id;
	private String name;
	private String description;
	private String currencyCode;
	private double price;
	private Calendar expire;
	boolean hasExpired;
	
	public Offer() {}
	public Offer(Integer id, String name, String description, String currencyCode, double price, Calendar expire, boolean hasExpired) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.currencyCode = currencyCode;
		this.price = price;
		this.expire = expire;
		this.hasExpired = hasExpired;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Calendar getExpire() {
		return expire;
	}
	public void setExpire(Calendar expire) {
		this.expire = expire;
	}
	public boolean isHasExpired() {
		return hasExpired;
	}
	public void setHasExpired(boolean hasExpired) {
		this.hasExpired = hasExpired;
	}
	
	@Override
	public String toString() {
		return "Offer [id=" + id + ", name=" + name + ", description=" + description + ", currencyCode=" + currencyCode
				+ ", price=" + price + ", expire=" + expire + ", hasExpired=" + hasExpired + "]";
	}
	
	
	
}
