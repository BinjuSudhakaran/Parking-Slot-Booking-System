package com.example.parkingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.parkingsystem.model.ParkingSlot;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot,Integer>
{

}
