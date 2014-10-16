package com.appdynamicspilot.service;

import org.apache.log4j.Logger;

import com.appdynamicspilot.model.User;
import com.appdynamicspilot.persistence.UserPersistence;
import com.appdynamicspilot.util.MD5;


public class UserService {
	private static final Logger log = Logger.getLogger(UserService.class);
	private UserPersistence userPersistence;

	public boolean validateMember(String email,String password){
		User existingMember = getMemberByLoginName(email);
		if(existingMember==null ||!existingMember.getPassword().equals(password)){
			return false;
		}
		return true;
	}

	public User getMemberByLoginName(String email) {
		return userPersistence.getMemberByEmail(email);
	}

	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}
}
