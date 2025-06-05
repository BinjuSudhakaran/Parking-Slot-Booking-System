package com.example.parkingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.parkingsystem.model.ParkingSlot;
@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot,Integer>
{
	
	List<ParkingSlot> findAll();
	
	
	@Query("SELECT s FROM ParkingSlot s WHERE s.shop.shopId = :shopId")
	List<ParkingSlot> findByShopId(@Param("shopId") int shopId);


}
