package com.appdynamicspilot.persistence;

import java.util.List;

import org.apache.log4j.Logger;

import com.appdynamicspilot.model.User;
import com.appdynamicspilot.util.ArgumentUtils;

import javax.persistence.*;

public class UserPersistence extends BasePersistenceImpl{
	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = Logger.getLogger(UserPersistence.class);

	public User getMemberByEmail(String email){
        Query q = getEntityManager().createQuery("SELECT u from User u WHERE u.email=:email");
        q.setParameter("email",email);
        List<User> memberList = (List<User>) q.getResultList();
		return (ArgumentUtils.isNullOrEmpty(memberList)?null:memberList.get(0)) ;
	}
}
