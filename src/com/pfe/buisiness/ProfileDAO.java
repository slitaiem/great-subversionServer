package com.pfe.buisiness;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.pfe.domain.Location;
import com.pfe.domain.PrivateService;
import com.pfe.domain.Profile;
import com.pfe.domain.User;

/**
 * Session Bean implementation class ProfileService
 */
@Stateless
@LocalBean
public class ProfileDAO implements ProfileDAORemote {

	@PersistenceContext(unitName="IBeaconModel")
	EntityManager entityManager;
    /**
     * Default constructor. 
     */
    public ProfileDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addProfile(Profile profile, List<User> users) {
		// TODO Auto-generated method stub
		List<User> users2 = new ArrayList<User>();
		entityManager.persist(profile);
		entityManager.flush();
		profile = entityManager.find(Profile.class, profile.getProfileId()) ;
		for (User user : users) {
			user = entityManager.find(User.class, user.getIdUser()) ;
			users2.add(user);
		}
		profile.addUsers(users2);

		entityManager.merge(profile);
		
		
		
	}
	
	@Override
	public void updateProfile(Profile profile) {
		// TODO Auto-generated method stub
		
		entityManager.merge(profile);
		
	}

	@Override
	public void addUsersToProfile(Profile profile, List<User> users) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excludeUsersFromProfile(Profile profile, List<User> users) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Profile findById(int id) {
		return entityManager.find(Profile.class, id);
	}

	@Override
	public List<Profile> getAllProfilesByUsersId(String idUser) {
		Query query = entityManager.createQuery("SELECT up FROM User u JOIN u.userProfiles up "
				+ " WHERE u.idUser = :userId ");
		query.setParameter("userId", idUser);
		query.toString();
		//query.unwrap(org.hibernate.).getQueryString()
		List<Profile> profiles = (List<Profile>) query.getResultList();
		return profiles;
	}

}
