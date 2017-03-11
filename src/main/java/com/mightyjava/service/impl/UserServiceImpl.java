package com.mightyjava.service.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.mightyjava.model.User;
import com.mightyjava.service.UserService;

public class UserServiceImpl implements UserService {

	@Inject
	private EntityManager entityManager;

	@Override
	public String authenticate(String userName, String password) {
		System.out.println("UserServiceImpl : authenticate");
		String status = null;
		String query = "FROM User WHERE userName=:userName and password=:password";
		User user = (User) entityManager.createQuery(query).setParameter("userName", userName)
				.setParameter("password", password).getSingleResult();
		System.out.println(user);
		if (user != null) {
			status = "Valid";
		} else {
			status = "Invalid";
		}
		return status;
	}

	@Override
	@Transactional
	public String save(User user) {
		System.out.println("UserServiceImpl : save");
		entityManager.persist(user);
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("status", "User Save successfully");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	@Override
	@Transactional
	public String list() {
		System.out.println("UserServiceImpl : list");
		List<User> users = entityManager.createQuery("FROM User").getResultList();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		try {
			for (User user : users) {
				JSONObject userJSON = new JSONObject();
				userJSON.put("userName", user.getUserName());
				userJSON.put("password", user.getPassword());
				jsonArray.put(userJSON);
			}
			jsonObject.put("users", jsonArray);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return jsonObject.toString();
	}

}
