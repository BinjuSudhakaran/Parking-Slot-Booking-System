package com.example.parkingsystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="shop")
public class Shop
{
	@Id	
	private int shopId;
	
	private String shopName;
	
	private int totalSlot;

	public int getShopId() 
	{
		return shopId;
	}
	public void setShopId(int shopId) 
	{
		this.shopId = shopId;
	}
	public String getShopName()
	{
		return shopName;
	}
	public void setShopName(String shopName) 
	{
		this.shopName = shopName;
	}
	public int getTotalSlot() {
		return totalSlot;
	}
	public void setTotalSlot(int totalSlot) {
		this.totalSlot = totalSlot;
	}
	
}
