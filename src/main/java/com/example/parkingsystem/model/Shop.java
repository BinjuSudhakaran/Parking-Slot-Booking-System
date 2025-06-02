package com.example.parkingsystem.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="shop")
public class Shop
{
	@Id	
	int shopId;
	
	String shopName;
	int slotsAvailable;
	
	@OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ParkingSlot> parkingSlots;

	public List<ParkingSlot> getParkingSlots()
	{
	    return parkingSlots;
	}
	public void setParkingSlots(List<ParkingSlot> parkingSlots)
	{
	    this.parkingSlots = parkingSlots;
	}
	public int getSlotsAvailable()
	{
		return slotsAvailable;
	}
	public void setSlotsAvailable(int slotsAvailable) 
	{
		this.slotsAvailable = slotsAvailable;
	}
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
	
}
