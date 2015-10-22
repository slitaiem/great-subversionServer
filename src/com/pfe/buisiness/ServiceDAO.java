package com.pfe.buisiness;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.pfe.domain.Location;
import com.pfe.domain.PrivateService;
import com.pfe.domain.Profile;
import com.pfe.domain.PublicService;
import com.pfe.domain.Service;
import com.pfe.domain.ServiceContent;
import com.pfe.domain.User;

/**
 * Session Bean implementation class ServiceService
 */
@Stateless
@LocalBean
public class ServiceDAO implements ServiceDAORemote {

	@EJB
	LocationDAO locationService;
	@EJB
	ProfileDAO profileService;
	
	@PersistenceContext(unitName="IBeaconModel")
	EntityManager entityManager;
    /**
     * Default constructor. 
     */
    public ServiceDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addPublicService(PublicService service, List<Location> locationList,List<Profile> profilesList) {
		// TODO Auto-generated method stub
		entityManager.persist(service);
		entityManager.flush();
		service = entityManager.find(PublicService.class, service.getIdService());
		List<Profile> profiles = new ArrayList<Profile>();
		List<Location> locations = new ArrayList<Location>();
	
		for (Profile profile : profilesList) {
			profile = entityManager.find(Profile.class, profile.getProfileId());
			profiles.add(profile);
		
		}
	
		for (Location location : locationList) {
			location =  entityManager.find(Location.class, location.getIdLocation());
			locations.add(location);
		}
		
		if(service instanceof ServiceContent  ){
			service.addProfiles(profiles);
			((ServiceContent)service).addLocations(locations);
		}
	
		entityManager.merge(service);
	}
	@Override
	public void addPrivateService(PrivateService service, List<Location> locationList,List<User> usersList) {
		// TODO Auto-generated method stub
		entityManager.persist(service);
		entityManager.flush();
		service = entityManager.find(PrivateService.class, service.getIdService());
		List<User> users = new ArrayList<User>();
		List<Location> locations = new ArrayList<Location>();
	
		for (User user : usersList) {
			user = entityManager.find(User.class, user.getIdUser());
			users.add(user);
		}
		for (Location location : locationList) {
			location =  entityManager.find(Location.class, location.getIdLocation());
			locations.add(location);
		}
		
		if(service instanceof ServiceContent  ){
			service.addUsers(users);
			service.addLocations(locations);
		}
	
		entityManager.merge(service);
	}
	@Override
	public List<PublicService> getPublicServices( int section , int floor, int profileId) {
		//Location location = locationService.findBySectionFloors(floor, section);
		
		Query query = entityManager.createQuery("SELECT ps FROM Profile p JOIN p.publicServices ps JOIN ps.locations l"
				+ " WHERE p.profileId = :profileId and l.section = :section and l.floor = :floor");
		query.setParameter("floor", floor); 
		query.setParameter("section", section); 
		query.setParameter("profileId", profileId);
		query.toString();
		//query.unwrap(org.hibernate.).getQueryString()
		List<PublicService> publicServices = (List<PublicService>) query.getResultList();
		
		return publicServices;

	}

	

	@Override
	public PrivateService findById(int serviceId) {
		return entityManager.find(PrivateService.class, serviceId);
	}

	@Override
	public List<PrivateService> getPrivateServices(int section, int floor,
			String userId) {
		Query query = entityManager.createQuery("SELECT ps FROM User u JOIN u.privateServices ps JOIN ps.locations l"
				+ " WHERE u.userId = :userId and l.section = :section and l.floor = :floor");
		query.setParameter("floor", floor); 
		query.setParameter("section", section); 
		query.setParameter("userId", userId);
		query.toString();
		//query.unwrap(org.hibernate.).getQueryString()
		List<PrivateService> privateServices = (List<PrivateService>) query.getResultList();
		
		return privateServices;
	}

	@Override
	public List<PublicService> getPublicServices(int profileId) {
		Query query = entityManager.createQuery("SELECT ps FROM Profile p JOIN p.publicServices ps JOIN ps.locations l"
				+ " WHERE p.profileId = :profileId ");
		query.setParameter("profileId", profileId);
		query.toString();
		//query.unwrap(org.hibernate.).getQueryString()
		List<PublicService> publicServices = (List<PublicService>) query.getResultList();
		
		return publicServices;
	}

	@Override
	public List<PrivateService> getPrivateServices(String userId) {
		Query query = entityManager.createQuery("SELECT ps FROM User u JOIN u.privateServices ps JOIN ps.locations l"
				+ " WHERE u.idUser = :userId ");
		query.setParameter("userId", userId);
		query.toString();
		//query.unwrap(org.hibernate.).getQueryString()
		List<PrivateService> privateServices = (List<PrivateService>) query.getResultList();
		
		return privateServices;
	}

	@Override
	public List<Object> getAllServices(String userId) {
		// TODO Auto-generated method stub
		List<PrivateService> privateServices = new ArrayList<PrivateService>();
		List<PublicService> publicServices = new ArrayList<PublicService>();
		privateServices =  getPrivateServices(userId);
		List<Object> serviceContent = new ArrayList<Object>();
		serviceContent.addAll(privateServices);
		List<Profile> profiles = profileService.getAllProfilesByUsersId(userId);
		for (Profile profile : profiles) {
			publicServices.addAll(getPublicServices(profile.getProfileId()));
		}
		serviceContent.addAll(publicServices);
		return serviceContent;
		
	}

	

}
