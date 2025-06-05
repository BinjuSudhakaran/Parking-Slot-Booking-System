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
	private int slotId;
	
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

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	

}
