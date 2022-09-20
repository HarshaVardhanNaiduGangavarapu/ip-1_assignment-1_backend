package com.authentication.RestController;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.authentication.Common.Common;
import com.authentication.POJO.User;
import com.authentication.Repository.UserRepository;

@RestController
public class uploadImagOfURLRestController {
	
	@Autowired
	UserRepository userRepo;

	@RequestMapping(value = "/rest/uploadImageOfURL", method = RequestMethod.POST, consumes = "multipart/form-data")
	public String UpdateUserInfo(String email ,String imgURL, HttpServletRequest req) {
		JSONObject json = new JSONObject();
		json.put("status", "failure");
		json.put("message", "Error");
		try {
			System.out.println("Email: " + email);
			
			URL url = new URL(imgURL);
	        ByteArrayOutputStream output = new ByteArrayOutputStream();
	          
	        try (InputStream inputStream = url.openStream()) {
	            int n = 0;
	            byte [] buffer = new byte[ 1024 ];
	            while (-1 != (n = inputStream.read(buffer))) {
	                output.write(buffer, 0, n);
	            }
	        }catch (Exception e) {
	        	System.out.println("Exception e" + e);
	        }
	      
			
			int cnt = userRepo.updateUserImageByEmail(email, output.toByteArray());
			if (cnt > 0) {
				json.put("status", "success");
				json.put("message", "Image Uploaded Successfully!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception e" + e);
		}
		return json.toString();
	}
}
