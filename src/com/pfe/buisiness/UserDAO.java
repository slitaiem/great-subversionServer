package com.pfe.buisiness;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sun.java2d.cmm.ProfileActivator;

import com.pfe.domain.Location;
import com.pfe.domain.Profile;
import com.pfe.domain.Student;
import com.pfe.domain.User;

/**
 * Session Bean implementation class UserService
 */
@Stateless
@LocalBean
public class UserDAO implements UserDAORemote {
	
	@PersistenceContext(unitName="IBeaconModel")
	EntityManager entityManager;
    /**
     * Default constructor. 
     */
    public UserDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addUser(User user) {
		entityManager.persist(user);
		
	}
	@Override
	public User findById(String id) {
		return entityManager.find(User.class, id);
	}
	
	@Override
	public void updateUser(Student user, Profile profile) {
		// TODO Auto-generated method stub
		user.getUserProfiles().add(profile);
		List<Profile>profiles = new ArrayList<Profile>();
		profiles.add(profile);
		findById(user.getIdUser()).addProfiles(profiles);
		entityManager.merge(user);
	}
	@Override
	public  User loginUser(String mailUser, String passwordUser) {
		String flag ="error";
		User user = new User();
		//join fetch u.userProfiles 
		Query q = entityManager.createQuery("SELECT u FROM User u   WHERE u.userEmail = :mailUser AND u.idUser = :passwordUser");
	     q.setParameter("mailUser", mailUser);
	     q.setParameter("passwordUser", passwordUser);
	     try{
	    	 
	       user = (User) q.getSingleResult();
	       
	     }catch(Exception e){      
	    	 flag =e.toString();
	     }
	     return user;
	}
	

	@Override
	public List<User> getUsers() {
		Query query = entityManager.createQuery("SELECT u FROM User u");
		List<User> users = query.getResultList();
	    return  users;
	}
	
	
	
	

}
