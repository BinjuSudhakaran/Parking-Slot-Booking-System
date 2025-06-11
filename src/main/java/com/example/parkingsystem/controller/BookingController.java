package com.example.parkingsystem.controller;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.parkingsystem.model.Booking;
import com.example.parkingsystem.model.RequestDTO;
import com.example.parkingsystem.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController
{
	@Autowired
    private BookingService bookingService;
	
	@PostMapping
	public ResponseEntity<Object> createBooking(@RequestBody RequestDTO requestDto) 
	{
	    try
	    {
	        Booking booking = bookingService.createBooking(requestDto);
	        
	        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
	    }
	    catch (IllegalArgumentException e)
	    {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    } 
	    catch (Exception e) 
	    {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
	    }
	}

	@GetMapping("/availability")
    public ResponseEntity<Object> checkAvailability(@RequestParam int shopId,@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date)
	{
        try 
        {
            int available = bookingService.getAvailableSlotCount(shopId, date);
            if (available > 0)
            {
                return ResponseEntity.ok(available + " slots available");
            }
            else
            {
                return ResponseEntity.status(409).body("No parking slots available for the selected date");
            }
        } 
        catch (Exception e) 
        {
            return ResponseEntity.status(500).body("An error occurred while checking availability");
        }
    }

    @DeleteMapping("/cancelbooking/{bookingId}")
    public ResponseEntity<Object> cancelBooking(@PathVariable int bookingId) 
    {
        try 
        {
            bookingService.cancelBooking(bookingId);
            
            return ResponseEntity.ok("Booking cancelled successfully");
        }
        catch (Exception e) 
        {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/park/{bookingId}")
    public ResponseEntity<Object> parkVehicle(@PathVariable int bookingId)
    {
        try
        {
            bookingService.parkVehicle(bookingId);
            
            return ResponseEntity.ok("Vehicle parked successfully");
        } 
        catch (IllegalStateException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        } 
        catch (Exception e) 
        {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
    
  
    @PutMapping("/exit/{bookingId}")
    public ResponseEntity<Object> exitVehicle(@PathVariable int bookingId)
    {
        try
        {
            bookingService.exitVehicle(bookingId);
            
            return ResponseEntity.ok("Vehicle exited successfully");
        } 
        catch (IllegalArgumentException e) 
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        } 
        catch (Exception e) 
        {
            return ResponseEntity.status(500).body("Unexpected error occurred");
        }
    }

}


