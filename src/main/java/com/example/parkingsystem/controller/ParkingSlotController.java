package com.example.parkingsystem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.parkingsystem.model.Shop;
import com.example.parkingsystem.service.ParkingSlotService;
import com.example.parkingsystem.service.ShopService;

@RestController
@RequestMapping("/park")
public class ParkingSlotController
{
	@Autowired
	private ShopService shopservice;
	
	@Autowired
    private ParkingSlotService parkingSlotService;
	
	@GetMapping("/searchshop")
	public ResponseEntity<List<Shop>> searchShops(@RequestParam String shopName)
	{
        return ResponseEntity.ok(shopservice.searchShops(shopName));
    }
	
	@PostMapping("/bookslot")
    public String bookSlot(@RequestParam int slotId, @RequestParam int userId)
	{
        return parkingSlotService.bookSlot(slotId, userId);
    }

}
