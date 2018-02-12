package org.bsframe.web.cxf;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class ServiceTest {

	public static void main(String[] args) throws Exception {
		JaxWsProxyFactoryBean webService = new JaxWsProxyFactoryBean();
		webService.setServiceClass(HelloWorldService.class);
		// http://localhost:8080/bsframe-web/cxf/HelloWorld?wsdl
		webService.setAddress("http://localhost:8080/bsframe-web/cxf/HelloWorld");
		HelloWorldService userService = (HelloWorldService) webService.create();
		System.out.println(userService.sayHello("testtttttttttt"));
	}
}