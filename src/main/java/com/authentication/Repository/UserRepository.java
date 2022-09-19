package com.authentication.Repository;

import org.springframework.data.repository.CrudRepository;

import com.authentication.Entity.UserMstEntity;



public interface UserRepository extends CrudRepository<UserMstEntity, Integer>{

	UserMstEntity findByUserEmail(String mobileNo);
}
