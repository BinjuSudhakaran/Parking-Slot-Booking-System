package com.example.parkingsystem.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.parkingsystem.model.Booking;
import com.example.parkingsystem.model.ParkingSlot;
import com.example.parkingsystem.model.RequestDTO;
import com.example.parkingsystem.model.User;
import com.example.parkingsystem.repository.BookingRepository;
import com.example.parkingsystem.repository.ParkingSlotRepository;
import com.example.parkingsystem.repository.UserRepository;

@Service
public class BookingService 
{
    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private UserRepository userRepository;
   
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
    
    public int getAvailableSlotCount(int shopId, LocalDate date)
    {
        List<ParkingSlot> allSlots = parkingSlotRepository.findByShopId(shopId);
        
        List<ParkingSlot> bookedSlots = bookingRepository.findBookedSlotsByShopAndDate(shopId, date);
        
        return allSlots.size() - bookedSlots.size();
    }

    public boolean areSlotsAvailable(LocalDate date)
    {
        List<ParkingSlot> allSlots = parkingSlotRepository.findAll();
        
        List<Booking> bookingsForDate = bookingRepository.findByBookingDate(date);
        
        return bookingsForDate.size() < allSlots.size();
    }

    public Booking getBookingById(int bookingId) 
    {
        return bookingRepository.findById(bookingId) .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public Booking createBooking(RequestDTO requestDto) 
    {
        if (requestDto.getDate() == null || requestDto.getDate().isBefore(LocalDate.now())) 
        {
            throw new IllegalArgumentException("Cannot book slot for a null date or past date");
        }

        if (!areSlotsAvailable(requestDto.getDate()))
        {
            throw new IllegalArgumentException("No parking slots available for the selected date");
        }

        if (requestDto.getSlotId() == 0 || requestDto.getUserId() == 0)
        {
            throw new IllegalArgumentException("Slot ID and User ID are required");
        }

        ParkingSlot slot = parkingSlotRepository.findById(requestDto.getSlotId()) .orElseThrow(() -> new IllegalArgumentException("Invalid Parking Slot ID"));

        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("Invalid User ID"));

        if (bookingRepository.existsByUserAndBookingDate(user, requestDto.getDate()))
        {
            throw new IllegalArgumentException("Already booked slot for this date");
        }

        if (bookingRepository.existsByParkingSlotAndBookingDate(slot, requestDto.getDate()))
        {
            throw new IllegalArgumentException("Slot already booked for this date");
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setParkingSlot(slot);
        booking.setShop(slot.getShop());
        booking.setBookingDate(requestDto.getDate());

        return bookingRepository.save(booking);
    }
    
   public void cancelBooking(int bookingId)
    {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found"));
        
        bookingRepository.delete(booking);
    }

    public void parkVehicle(int bookingId) 
    {
        Booking booking = bookingRepository.findById(bookingId)  .orElseThrow(() -> new RuntimeException("Booking not found"));
        
        if(booking.isParked()&&booking.isExited())
        {
        	throw new IllegalStateException("Vehicle already parked and exited for this booking");
        }

        if (booking.isParked())
        {
            throw new IllegalStateException("Vehicle already parked for this booking");
        }
       
        booking.setParked(true);
        
        bookingRepository.save(booking);
    }
    
    public void exitVehicle(int bookingId)
    {
        Booking booking = getBookingById(bookingId);
        
        if (booking == null) 
        {
            throw new IllegalArgumentException("Booking not found");
        }

        if (!booking.isParked())
        {
            throw new IllegalArgumentException("Vehicle not parked yet");
        }

        if (booking.isExited())
        {
            throw new IllegalArgumentException("Vehicle already exited");
        }

        booking.setExited(true);
        //booking.setParked(false);

        updateBooking(booking);
    }
    public void updateBooking(Booking booking)
    {
        bookingRepository.save(booking); 
    }
}