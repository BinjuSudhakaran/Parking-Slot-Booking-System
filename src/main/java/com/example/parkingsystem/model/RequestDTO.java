package com.example.parkingsystem.model;

import java.time.LocalDate;

public class RequestDTO 
{
	private Integer userId;
	private Integer slotId;
	private LocalDate date;
	public int getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public int getSlotId() {
		return slotId;
	}
	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

}
