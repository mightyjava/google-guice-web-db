package com.mightyjava.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.inject.Inject;
import com.mightyjava.model.User;
import com.mightyjava.service.GuiceService;
import com.mightyjava.service.UserService;

@Path("/guice")
public class GuiceController {

	@Inject
	private GuiceService guiceService;

	@Inject
	private UserService userService;
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public String list() {
		System.out.println("GuiceController : list");
		return userService.list();
	}

	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String save(JSONObject jsonObject) throws JSONException {
		System.out.println("GuiceController : save");
		User user = new User();
		user.setUserName(jsonObject.getString("userName"));
		user.setPassword(jsonObject.getString("password"));
		return userService.save(user);
	}

	@POST
	@Path("/authbyjson")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String authenticate(JSONObject jsonObject) throws JSONException {
		return userService.authenticate(jsonObject.getString("userName"), jsonObject.getString("password"));
	}

	@GET
	@Path("/authbyquery")
	@Produces(MediaType.TEXT_PLAIN)
	public String authenticate(@QueryParam("userName") String userName, @QueryParam("password") String password) {
		return userService.authenticate(userName, password);
	}

	@GET
	@Path("/authbyuri")
	@Produces(MediaType.TEXT_PLAIN)
	public String authenticate(@Context UriInfo uriInfo) {
		return userService.authenticate(uriInfo.getQueryParameters().getFirst("userName"),
				uriInfo.getQueryParameters().getFirst("password"));
	}

	@GET
	@Path("/sayhello")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return guiceService.sayHello();
	}

}
