package com.authentication.RestController;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.POJO.User;
import com.authentication.Repository.UserRepository;
import com.authentication.Service.loginService;


@RestController
public class LoginRestController {
	
	@Autowired
	UserRepository userrepo;

	@Autowired
	DataSource datasource;

	@RequestMapping(value = "/rest/checklogin", method = RequestMethod.POST , consumes = "multipart/form-data")
	@ResponseBody
	public String CheckLogin(User user, HttpServletRequest req) {
		JSONObject res = new JSONObject();
		try {
			loginService service = new loginService();

			service.setDatasource(datasource);
			if (service.checkLogin(user, userrepo)) {
				System.out.println(" true login");

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
