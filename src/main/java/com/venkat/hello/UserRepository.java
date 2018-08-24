package com.venkat.hello;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.venkat.spring.entity.User;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer>{
	public List<User> findAll();
	public String addNewUser(String name, String email);
}
