package com.authentication.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@Autowired
	DataSource datasource;
	
	@RequestMapping("/")
	public String home(HttpServletRequest req) {
		System.out.println("Inside");
		return "Login";
	}
}
