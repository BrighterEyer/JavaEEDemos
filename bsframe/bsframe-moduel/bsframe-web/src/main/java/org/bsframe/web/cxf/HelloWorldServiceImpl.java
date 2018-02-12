package org.bsframe.web.cxf;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.bsframe.entity.User;
import org.bsframe.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(endpointInterface = "org.bsframe.web.cxf.HelloWorldService", serviceName = "helloWorldService")
public class HelloWorldServiceImpl implements HelloWorldService {

	@Autowired
	private UserServiceImpl userServiceImpl;

	public String sayHello(@WebParam(name = "userName") String userName) {
		User user = userServiceImpl.findUserById(2);
		System.out.println(user);
		System.out.println("HelloWorldServiceImpl.sayHello(" + userName + ")");
		return "Hello " + userName;
	}
}