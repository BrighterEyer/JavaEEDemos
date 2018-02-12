package org.bsframe.web.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bsframe.entity.User;
import org.springframework.stereotype.Component;

@Component("restSample")
public class CxfController {

	@GET
	@Path("/order")
	@Produces({ MediaType.APPLICATION_JSON })
	public User findUser() {
		User user = new User();
		user.setUsername("zouhao");
		user.setPassword("zouhao");
		return user;
	}
}
