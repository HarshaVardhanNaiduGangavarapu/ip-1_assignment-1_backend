package com.authentication.RestController;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.POJO.User;
import com.authentication.Repository.UserRepository;
import com.authentication.Service.loginService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;


@RestController
@JsonAutoDetect
//@CrossOrigin(origins="*")
public class LoginRestController {
	
	@Autowired
	UserRepository userrepo;

	@Autowired
	DataSource datasource;

	@RequestMapping(value = "/rest/checklogin", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE,consumes="*/*")
	@ResponseBody
	public String CheckLogin(User user, HttpServletRequest req) {
		JSONObject res = new JSONObject();
//		try {
//			System.out.println(req.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		try {
			loginService service = new loginService();

			service.setDatasource(datasource);
			if (service.checkLogin(user, userrepo)) {
				System.out.println(" true login");
				System.out.println(user.getUserEmail());
				System.out.println(user.getPassword());

				res.put("userDetail", new JSONObject(user));
				
				res.put("message", service.getMessage());
				res.put("status", "success");

			} else {
				System.out.println(" false login");
				res.put("message", service.getMessage());
				res.put("status", "failure");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		System.out.println("before login response");
		return res.toString();
	}
}
