package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.domain.Entry;
import edu.mum.domain.UserLogin;
import edu.mum.rest.service.EntryRestService;
import edu.mum.rest.service.UserRestService;
import edu.mum.service.UserLoginService;

@Service
@Transactional
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	private UserRestService userLoginRestService;
	
	@Override
	public boolean login(UserLogin user) {
		// TODO Auto-generated method stub
		
		userLoginRestService.login(user);
		return false;
	}
	
//	@Autowired
//	private EntryRestService entryRestService;
//	
//	@Override
//	public List<Entry> findAll() {
//		// TODO Auto-generated method stub
//		return entryRestService.findAll();
//	}

}
