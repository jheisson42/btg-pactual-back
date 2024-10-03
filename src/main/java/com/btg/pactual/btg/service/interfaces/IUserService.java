package com.btg.pactual.btg.service.interfaces;

import com.btg.pactual.btg.models.User;
import java.util.List;


public interface IUserService {
	
	void insertUser(User user);
	List<User> getAllUsers();
	User getUserById(String id);
	void deleteUser(String idUser);
}
