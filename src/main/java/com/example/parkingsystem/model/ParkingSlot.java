package com.example.parkingsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="parkingslot")
public class ParkingSlot
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int slotId;
	
	boolean booked=true;
	
	@ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId") 
    private User user;

    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "shopId")
    @JsonIgnore
    private Shop shop;

	public int getSlotId() {
		return slotId;
	}

	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}

	public boolean isBooked() {
		return booked;
	}

	public void setBooked(boolean booked) {
		this.booked = booked;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	

}
