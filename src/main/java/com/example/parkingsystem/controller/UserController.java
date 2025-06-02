package com.example.parkingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parkingsystem.model.User;
import com.example.parkingsystem.service.UserService;

@RestController
//@RequestMapping("/user")
public class UserController 
{
	@Autowired
	private UserService service;
	
	@GetMapping("/user")
	public List<User> getAllUser()
	{
		return service.getAllUser();
		
	}
	@PostMapping("/user")
	public User createUser(@RequestBody User user)
	{
		return service.createUser(user);
	}
	

}
