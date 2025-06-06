package com.example.parkingsystem.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

@Entity
@Table(name="booking")
public class Booking 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	
	@ManyToOne
	@JoinColumn(name="user_id",referencedColumnName="userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="shop_id",referencedColumnName="shopId")
	private Shop shop;
	
	@ManyToOne
	@JoinColumn(name="slot_id",referencedColumnName="slotId")
	private ParkingSlot parkingSlot;
	
	private LocalDate bookingDate;
	
	private boolean parked = false;

	public boolean isParked() {
		return parked;
	}

	public void setParked(boolean parked) {
		this.parked = parked;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
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

	public ParkingSlot getParkingSlot() {
		return parkingSlot;
	}

	public void setParkingSlot(ParkingSlot parkingSlot) {
		this.parkingSlot = parkingSlot;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	

	


}
