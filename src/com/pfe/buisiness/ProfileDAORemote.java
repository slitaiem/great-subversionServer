package com.pfe.buisiness;

import java.util.List;

import javax.ejb.Remote;

import com.pfe.domain.Profile;
import com.pfe.domain.User;

@Remote
public interface ProfileDAORemote {


	void updateProfile(Profile profile);

	void addProfile(Profile profile, List<User> users);
	
	void addUsersToProfile(Profile profile, List<User> users);
	
	void excludeUsersFromProfile(Profile profile,List<User> users);
	
	Profile findById(int id);
	
	List<Profile> getAllProfilesByUsersId(String idUser);

}
