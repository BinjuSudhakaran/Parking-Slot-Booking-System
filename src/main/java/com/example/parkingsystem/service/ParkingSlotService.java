package com.example.parkingsystem.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.parkingsystem.model.ParkingSlot;
import com.example.parkingsystem.model.Shop;
import com.example.parkingsystem.model.User;
import com.example.parkingsystem.repository.ParkingSlotRepository;
import com.example.parkingsystem.repository.ShopRepository;
import com.example.parkingsystem.repository.UserRepository;
@Service
public class ParkingSlotService 
{
	
	@Autowired
	private ShopRepository shoprepository;
	
	@Autowired
	private ParkingSlotRepository parkingSlotRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Shop> searchShops(String shopName) 
	{
	    return shoprepository.findByShopNameWithSlots(shopName);
	}

	public String bookSlot(int slotId, int userId) 
	{
	    Optional<ParkingSlot> optionalSlot = parkingSlotRepository.findById(slotId);
	    Optional<User> optionalUser = userRepository.findById(userId);

	    if (optionalSlot.isEmpty()) return "Slot not found.";
	    if (optionalUser.isEmpty()) return "User not found.";

	    ParkingSlot slot = optionalSlot.get();

	    if (slot.isBooked()) 
	    {
	        return "Slot is already booked.";
	    }

	   
	    Shop shop = slot.getShop(); 

	    if (shop.getSlotsAvailable()== 0) 
	    {
	        return "No available slots in the shop.";
	    }

	    slot.setBooked(true);
	    slot.setUser(optionalUser.get());
	    shop.setSlotsAvailable(shop.getSlotsAvailable() - 1);
	    parkingSlotRepository.save(slot);
	    shoprepository.save(shop);

	    return "Slot " + slotId + " successfully booked by User " + userId;
	}

}
