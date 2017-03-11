package com.mightyjava.service;

import com.google.inject.ImplementedBy;
import com.mightyjava.model.User;
import com.mightyjava.service.impl.UserServiceImpl;

@ImplementedBy(UserServiceImpl.class)
public interface UserService {
	String authenticate(String userName, String password);
	
	String save(User user);
	
	String list();
}
