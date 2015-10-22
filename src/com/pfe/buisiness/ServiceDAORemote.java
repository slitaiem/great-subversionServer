package com.pfe.buisiness;

import java.util.List;

import javax.ejb.Remote;

import com.pfe.domain.Location;
import com.pfe.domain.PrivateService;
import com.pfe.domain.Profile;
import com.pfe.domain.PublicService;
import com.pfe.domain.Service;
import com.pfe.domain.ServiceContent;
import com.pfe.domain.User;

@Remote
public interface ServiceDAORemote {
	
	List<PublicService> getPublicServices(int section, int floor, int profileId);
	List<PublicService> getPublicServices( int profileId);
	List<PrivateService> getPrivateServices(int section, int floor, String userId);
	List<PrivateService> getPrivateServices(String userId);
	PrivateService findById(int serviceId);
	void addPrivateService(PrivateService service, List<Location> locationList,
			List<User> usersList);
	void addPublicService(PublicService service, List<Location> locationList,
			List<Profile> profilesList);
	List<Object> getAllServices(String userId);
}
