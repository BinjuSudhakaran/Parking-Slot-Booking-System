package com.example.parkingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.parkingsystem.model.ParkingSlot;
import com.example.parkingsystem.model.Shop;
import com.example.parkingsystem.repository.ParkingSlotRepository;
import com.example.parkingsystem.repository.ShopRepository;

@Service
public class ShopService 
{
	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private ParkingSlotRepository parkingSlotRepository;
	
	
	public List<Shop> getAllShop() 
	{
		
		return shopRepository.findAll();
	}

	public Shop addShop(Shop shop) 
	{
        // Save the shop first
        Shop savedShop = shopRepository.save(shop);

        // Create parking slots for this shop
        for (int i = 0; i < shop.getSlotsAvailable(); i++)
        {
            ParkingSlot slot = new ParkingSlot();
            slot.setBooked(false);  // available by default
            slot.setShop(savedShop);
            parkingSlotRepository.save(slot);
        }

        return savedShop;
    }
	
	public List<Shop> searchShops(String shopName) 
	 {
        return shopRepository.findByshopNameContainingIgnoreCase(shopName);
    }

}
