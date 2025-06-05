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

	public List<Shop> searchShops(String shopName) 
	 {
        return shopRepository.findByshopNameContainingIgnoreCase(shopName);
    }

	public Shop createShop(Shop shop)
	{
		
		Shop savedShop = shopRepository.save(shop);
		for(int i=0;i<shop.getTotalSlot();i++)
		{
			ParkingSlot slot = new ParkingSlot();
			slot.setShop(savedShop);
			parkingSlotRepository.save(slot);		
		}
		return savedShop;
	}
	
}
