package edu.mum.rest.service;

import edu.mum.domain.UserCredentials;
import edu.mum.domain.UserLogin;

public interface UserRestService {
	public boolean login(UserLogin user);
}
