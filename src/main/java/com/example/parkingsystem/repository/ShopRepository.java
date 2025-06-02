package com.example.parkingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.parkingsystem.model.Shop;

@Repository

public interface ShopRepository extends JpaRepository<Shop,Integer>
{
	List<Shop> findByshopNameContainingIgnoreCase(String shopName);
	//SELECT * FROM shop WHERE LOWER(shop_name) LIKE LOWER('%input%');


	@Query("SELECT s FROM Shop s LEFT JOIN FETCH s.parkingSlots WHERE LOWER(s.shopName) LIKE LOWER(CONCAT('%', :shopName, '%'))")
	List<Shop> findByShopNameWithSlots(@Param("shopName") String shopName);


}
