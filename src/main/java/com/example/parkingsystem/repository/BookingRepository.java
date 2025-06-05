package com.example.parkingsystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.parkingsystem.model.Booking;
import com.example.parkingsystem.model.ParkingSlot;
import com.example.parkingsystem.model.User;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>
{
	
    List<Booking> findByBookingDate(LocalDate bookingDate);
	
	boolean existsByUserAndBookingDate(User user, LocalDate bookingDate);
	
	boolean existsByParkingSlotAndBookingDate(ParkingSlot parkingSlot, LocalDate bookingDate);
	
	@Query("SELECT b.parkingSlot FROM Booking b WHERE b.bookingDate = :date AND b.parkingSlot.shop.shopId = :shopId")
	List<ParkingSlot> findBookedSlotsByShopAndDate(@Param("shopId") int shopId, @Param("date") LocalDate date);

	
}
