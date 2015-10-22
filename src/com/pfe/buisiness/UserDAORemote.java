package com.pfe.buisiness;

import java.util.List;

import javax.ejb.Remote;

import com.pfe.domain.Profile;
import com.pfe.domain.Student;
import com.pfe.domain.User;
@Remote
public interface UserDAORemote {
	
	void addUser(User user);

	User findById(String id);

	void updateUser(Student user, Profile profile);

	User loginUser(String mailUser, String passwordUser);
	
	List<User> getUsers();


}
