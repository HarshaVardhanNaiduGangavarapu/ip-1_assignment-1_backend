package com.authentication.Service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.authentication.Common.Common;
import com.authentication.Entity.UserMstEntity;
import com.authentication.POJO.User;
import com.authentication.Repository.UserRepository;

@Service
public class RegistrationService {

	String message = "";
	UserRepository userrepo;
	HttpServletRequest request;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public UserRepository getUserrepo() {
		return userrepo;
	}

	public void setUserrepo(UserRepository userrepo) {
		this.userrepo = userrepo;
	}
	
	
	public boolean ValidateData(User user) {
		boolean flag = true;
		try {

//			if (user.getLogoObj().length == 0) {
//				flag = false;
//				message = message + "Logo size is zero<br>";
//			}
			if (!user.getPassword().equalsIgnoreCase(user.getConfirmPassword())) {
				flag = false;
				this.message = "Password and confirm password is not same.";

			}

		} catch (Exception e) {
			System.out.println("Exception in ValidateData " + e);
		}
		return flag;
	}
	
	public boolean CheckForDuplicate(User user, UserRepository userrepo) {
		boolean flag = true;
		try {
			UserMstEntity usermst = userrepo.findByUserEmail(user.getUserEmail());
			if (usermst != null && usermst.getUserEmail().equalsIgnoreCase(user.getUserEmail())) {
				flag = false;
				this.message = "You are already registered with this Email Id.";
			}
		} catch (Exception e) {
			flag=false;
			System.out.println("Exception in checkForDuplicate " + e);
		}
		return flag;
	}
	
	public boolean InsertRegistraionData(User user, UserRepository userrepo) {
		System.out.println(user.getUserName());
		System.out.println(user.getLogoObj().length);
		boolean flag = false;
		try {
			UserMstEntity ume = new UserMstEntity();
			ume.setUserEmail(user.getUserEmail());
			ume.setPassword(Common.encryptStringAdvance(user.getPassword()));
			ume.setUserName(user.getUserName());
			ume.setUserMobileNo(user.getUserMobileNo());
			ume.setCreatedTime(Common.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
			ume.setUserBio(user.getUserBio());
			ume.setLogo(user.getLogoObj());
			if(userrepo.save(ume) != null) {
				flag = true;	
			}
		}catch(Exception e) {
			flag=false;
			System.out.println("Exception in InsertRegistraionData " + e);
		}
		return flag;
	}
	
}
