package com.example.parkingsystem.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.parkingsystem.model.Booking;
import com.example.parkingsystem.model.ParkingSlot;
import com.example.parkingsystem.model.User;
import com.example.parkingsystem.repository.BookingRepository;
import com.example.parkingsystem.repository.ParkingSlotRepository;

@Service
public class BookingService 
{
    @Autowired
    private BookingRepository bookingRepository;
    
    
   
    @Autowired
    private ParkingSlotRepository parkingSlotRepository;
    

    public boolean existsByUserAndBookingDate(User user, LocalDate bookingDate)
    {
        return bookingRepository.existsByUserAndBookingDate(user, bookingDate);     
    }

    public boolean isSlotAlreadyBooked(ParkingSlot parkingSlot, LocalDate bookingDate) 
    {
        return bookingRepository.existsByParkingSlotAndBookingDate(parkingSlot, bookingDate);
    }

    public Booking createBooking(Booking booking) 
    {
        return bookingRepository.save(booking);
    }
    
     
    public boolean areSlotsAvailable(LocalDate date)
    {
        List<ParkingSlot> allSlots = parkingSlotRepository.findAll();
        List<Booking> bookingsForDate = bookingRepository.findByBookingDate(date);

        return bookingsForDate.size() < allSlots.size();  // true if at least one slot is free
    }
    
    public int getAvailableSlotCount(int shopId, LocalDate date)
    {
        List<ParkingSlot> allSlots = parkingSlotRepository.findByShopId(shopId);
        List<ParkingSlot> bookedSlots = bookingRepository.findBookedSlotsByShopAndDate(shopId, date);

        int available = allSlots.size() - bookedSlots.size();
        return available;
    }
    public Booking getBookingById(int bookingId) 
    {
        return bookingRepository.findById(bookingId).orElse(null);
    }

    public void cancelBooking(int bookingId) 
    {
        bookingRepository.deleteById(bookingId);
    }
    public void updateBooking(Booking booking)
    {
        bookingRepository.save(booking); // Save updated parking status
    }



}