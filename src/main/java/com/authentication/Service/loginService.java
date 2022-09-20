package com.authentication.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.authentication.Common.Common;
import com.authentication.Entity.UserMstEntity;
import com.authentication.POJO.User;
import com.authentication.Repository.UserRepository;




@Service
public class loginService {
	
	DataSource datasource;
	String message = "";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}
	
	public boolean checkLogin(User user, UserRepository userrepo) {
		boolean flag = false;
		try {
			UserMstEntity umeEntity = checkLogin(user.getUserEmail(), Common.encryptStringAdvance(user.getPassword()));
			if(umeEntity != null && umeEntity.getStatus()==1) {
				flag = true;
				user.setUserEmail(umeEntity.getUserEmail());
				user.setUserMobileNo(umeEntity.getUserMobileNo());
				user.setUserName(umeEntity.getUserName());
				user.setSrno(umeEntity.getSrno());
				user.setUserBio(umeEntity.getUserBio());
				user.setPhoto(umeEntity.getPhoto());
				this.message = "The user is logged in successfully.";
			}else if(umeEntity.getStatus()==0) {
				flag = false;
				this.message = "The user is temporarily blocked.";
			}
			else {
				flag = false;
				this.message = "Invalid userid or password";
			}
			
		} catch (Exception e) {
			System.out.println("Exception in checkLogin " + e);
			this.message = "Invalid userid or password";
			flag = false;
		}
		return flag;
	}
	
	public UserMstEntity checkLogin(String userEmail, String password) {
		UserMstEntity user = null;
		Connection cn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			cn = datasource.getConnection();
			st = cn.prepareStatement("select * from user_mst u where u.email = ? and u.password = ?");
			st.setString(1, userEmail);
			st.setString(2, password);

			rs = st.executeQuery();
			if (rs.next()) {
				user = new UserMstEntity();
				user.setUserEmail(rs.getString("email"));
				user.setUserMobileNo(rs.getString("mobile_no"));
				user.setUserName(rs.getString("name"));
				user.setSrno(rs.getInt("sr_no"));
				user.setUserBio(rs.getString("bio"));
				user.setPhoto(rs.getBytes("photo"));
				user.setStatus(rs.getInt("status"));
			}
		} catch (Exception e) {
			System.out.println("Exception  checkLogin " + e);
		} finally {
			try {
				if (cn != null) {
					cn.close();
				}
				if (st != null) {
					st.close();
				}
				if (rs != null) {
					rs.close();
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return user;
	}

}
