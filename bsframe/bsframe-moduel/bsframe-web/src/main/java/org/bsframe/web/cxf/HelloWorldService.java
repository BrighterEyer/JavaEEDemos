package org.bsframe.web.cxf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface HelloWorldService {
	@WebMethod
	public String sayHello(@WebParam(name = "username") String username);

}