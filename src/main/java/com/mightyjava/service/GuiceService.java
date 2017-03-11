package com.mightyjava.service;

import com.google.inject.ImplementedBy;
import com.mightyjava.service.impl.GuiceServiceImpl;

@ImplementedBy(GuiceServiceImpl.class)
public interface GuiceService {
	String sayHello();
}
