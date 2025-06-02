package com.example.parkingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.parkingsystem.model.User;
import com.example.parkingsystem.repository.UserRepository;

@Service
public class UserService
{
	@Autowired
	private UserRepository repository;
	public List<User> getAllUser()
	{		
		return repository.findAll();
	}
	public User createUser(User user) 
	{
		return repository.save(user);
	}
}
