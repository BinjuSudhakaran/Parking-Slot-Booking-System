package com.example.parkingsystem.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.parkingsystem.model.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop,Integer>
{
	List<Shop> findByshopNameContainingIgnoreCase(String shopName);
}
