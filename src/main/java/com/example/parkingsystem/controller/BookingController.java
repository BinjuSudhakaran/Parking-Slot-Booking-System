package com.example.parkingsystem.controller;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.parkingsystem.model.Booking;
import com.example.parkingsystem.model.ParkingSlot;
import com.example.parkingsystem.model.User;
import com.example.parkingsystem.repository.ParkingSlotRepository;
import com.example.parkingsystem.repository.UserRepository;
import com.example.parkingsystem.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController
{

	@Autowired
    private BookingService bookingService;
	
	@Autowired
	private ParkingSlotRepository parkingSlotRepository;

	@Autowired
	private UserRepository userRepository;

	@PostMapping
	public ResponseEntity<Object> createBooking(@Validated @RequestBody Booking booking) 
	{
	    if (booking.getBookingDate() == null || booking.getBookingDate().isBefore(LocalDate.now()))
	    {
	        return ResponseEntity.badRequest().body("Can not book slot for a null date orpast date");
	    }
   
	    if (!bookingService.areSlotsAvailable(booking.getBookingDate()))
	    {
	        return ResponseEntity.status(409).body("No parking slots available for the selected date");
	    }
	    
	    if (booking.getParkingSlot() == null || booking.getParkingSlot().getSlotId() == 0 ||booking.getUser() == null || booking.getUser().getUserId() == 0)
	    {
	        return ResponseEntity.badRequest().body("Slot ID and User ID are required");
	    }

	   
	    ParkingSlot fullSlot = parkingSlotRepository.findById(booking.getParkingSlot().getSlotId()).orElse(null);
	    if (fullSlot == null) 
	    {
	        return ResponseEntity.badRequest().body("Invalid Parking Slot ID");
	    }

	    
	    User user = userRepository.findById(booking.getUser().getUserId()).orElse(null);
	    if (user == null) 
	    {
	        return ResponseEntity.badRequest().body("Invalid User ID");
	    }

	    if (bookingService.existsByUserAndBookingDate(user, booking.getBookingDate())) 
	    {
	        return ResponseEntity.status(409).body("Already booked slot for this date");
	    }

	    if (bookingService.isSlotAlreadyBooked(fullSlot, booking.getBookingDate()))
	    {
	        return ResponseEntity.status(409).body("Slot already booked for this date");
	    }

	    booking.setUser(user);
	    booking.setParkingSlot(fullSlot);
	    booking.setShop(fullSlot.getShop());

	    Booking savedBooking = bookingService.createBooking(booking);
	    return ResponseEntity.status(201).body(savedBooking);
	}

	
	@GetMapping("/availability")
	public ResponseEntity<Object> checkAvailability(@RequestParam int shopId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) 
	{
	    int available = bookingService.getAvailableSlotCount(shopId, date);
	    
	    if (available > 0)
	    {
	        return ResponseEntity.ok(available + " slots available");
	    } else 
	    {
	        return ResponseEntity.status(409).body("No parking slots available for the selected date");
	    }
	}

	
	
	
	
	
	
	
	
	
	
	
}


