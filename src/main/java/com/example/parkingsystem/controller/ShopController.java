package com.example.parkingsystem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.parkingsystem.model.Shop;
import com.example.parkingsystem.service.ShopService;

@RestController
@RequestMapping("/shop")
public class ShopController 
{
		@Autowired
		private ShopService shopService;
		
		@GetMapping
		public List<Shop> getAllShop()
		{
			return shopService.getAllShop();			
		}
		@PostMapping
		public Shop createShop(@RequestBody Shop shop)
		{
			return shopService.createShop(shop);
		}
		
		@GetMapping("/search")
	    public ResponseEntity<List<Shop>> searchShops(@RequestParam String shopName)
		{
	        return ResponseEntity.ok(shopService.searchShops(shopName));
	    }
}


