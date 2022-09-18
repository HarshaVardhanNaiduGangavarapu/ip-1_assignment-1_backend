package com.authentication.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="user_mst")
public class UserMstEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sr_no", columnDefinition = "INT(11)")
	private int srno;

	@Column(name = "name", columnDefinition = "varchar(100)")
	private String userName;
	
	@Column(name = "email", columnDefinition = "varchar(100)")
	private String userEmail;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "created_time")
	private String createdTime;
	
	@Column(name = "bio", columnDefinition = "varchar(1000)")
	private String userBio;
	
	@Column(name = "mobile_no", columnDefinition = "varchar(15)")
	private String userMobileNo;
	
	@Lob
	@Column(name = "photo", length = 100000)
	private byte[] userPhoto;
	
	public int getSrno() {
		return srno;
	}

	public void setSrno(int srno) {
		this.srno = srno;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobileNo() {
		return userMobileNo;
	}

	public void setUserMobileNo(String userMobileNo) {
		this.userMobileNo = userMobileNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserBio() {
		return userBio;
	}

	public void setUserBio(String userBio) {
		this.userBio = userBio;
	}

	public byte[] getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(byte[] userPhoto) {
		this.userPhoto = userPhoto;
	}
	
}
