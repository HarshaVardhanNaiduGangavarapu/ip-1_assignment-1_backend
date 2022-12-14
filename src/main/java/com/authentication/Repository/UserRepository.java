package com.authentication.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.authentication.Entity.UserMstEntity;



public interface UserRepository extends CrudRepository<UserMstEntity, Integer>{

	UserMstEntity findByUserEmail(String mobileNo);
	
	@Transactional
	@Modifying
	@Query(value = "delete from user_mst where email = :email", nativeQuery = true)
	int deleteUserEmail(@Param("email") String userEmail);
	
	@Transactional
	@Modifying
	@Query(value = "update user_mst set status=0 where email = :email", nativeQuery = true)
	int blockUser(@Param("email") String userEmail);
	
	@Transactional
	@Modifying
	@Query(value = "update user_mst u set u.name= :name ,u.email = :email ,u.password = :password,"
			+ "u.bio = :bio,u.mobile_no = :mobileNo,u.photo = :photo where u.email = :email", nativeQuery = true)
	int updateUserRestByEmail(@Param("name") String userName, @Param("email") String userEmail, @Param("password") String password,
			@Param("bio") String userBio, @Param("mobileNo") String mobileNo,
			@Param("photo") byte[] imageFile);
	
	@Transactional
	@Modifying
	@Query(value = "update user_mst u set u.photo = :photo where u.email = :email", nativeQuery = true)
	int updateUserImageByEmail(@Param("email") String userEmail, @Param("photo") byte[] imageFile);
}
