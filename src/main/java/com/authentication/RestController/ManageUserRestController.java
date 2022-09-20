package com.authentication.RestController;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.authentication.POJO.User;
import com.authentication.Repository.UserRepository;



@RestController
public class ManageUserRestController {
	
	@Autowired
	UserRepository userRepo;

	@RequestMapping(value = "/rest/updateUserInfo", method = RequestMethod.POST, consumes = "multipart/form-data")
	public String UpdateUserInfo(User user, @RequestParam(value = "image", required = false) MultipartFile imageFile, HttpServletRequest req) {
		JSONObject json = new JSONObject();
		json.put("status", "failure");
		json.put("message", "Error");
		try {
			System.out.println("Name:" + user.getUserName());
			System.out.println("Email: " + user.getUserEmail());
			System.out.println("Bio: " + user.getUserBio());
			System.out.println("Mobile:"  + user.getUserMobileNo());
			System.out.println("image:"  + imageFile.getBytes().length);
			int cnt = userRepo.updateUserRestByEmail(user.getUserName(), user.getUserEmail(), user.getUserBio(), user.getUserMobileNo(), imageFile.getBytes());
			if (cnt > 0) {
				json.put("status", "success");
				json.put("message", "User updated successfully.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception e" + e);
		}
		return json.toString();
	}
}
