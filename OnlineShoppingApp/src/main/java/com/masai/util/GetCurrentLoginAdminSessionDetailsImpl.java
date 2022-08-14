package com.masai.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.masai.exceptions.AdminException;
import com.masai.model.Admin;
import com.masai.model.AdminCurrentUserSession;
import com.masai.repository.AdminDao;
import com.masai.repository.AdminSessionDao;



@Component
public class GetCurrentLoginAdminSessionDetailsImpl implements GetCurrentLoginAdminSessionDetailsInerface{

	
	@Autowired
	private AdminSessionDao sessionDAO;

	@Autowired
	private AdminDao adminDAO;
	
	
	public AdminCurrentUserSession getCurrentUserSession(String key) {
		Optional<AdminCurrentUserSession> optional = sessionDAO.findByUuid(key);
		
		if(!optional.isPresent()) {
			throw new AdminException("Unauthorized");
		}
		
		return optional.get();
	}
	
	public Integer getCurrentUserSessionAdminId(String key){
		Optional<AdminCurrentUserSession> optional = sessionDAO.findByUuid(key);
		
		if(!optional.isPresent()) {
			throw new AdminException("Unauthorized");
		}
		
		return optional.get().getAdminId();
	}
	
	public Admin getCurrentAdmin(String key) {
		Optional<AdminCurrentUserSession> optional = sessionDAO.findByUuid(key);
		
		if(!optional.isPresent()) {
			throw new AdminException("Unauthorized");
		}
		
		Integer adminId = optional.get().getAdminId();
		
		return  adminDAO.getById(adminId);
	}

}
