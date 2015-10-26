package com.pfe.buisiness;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.pfe.domain.Location;
import com.pfe.domain.User;

/**
 * Session Bean implementation class  LocationService
 */
@Stateless 
@LocalBean
public class LocationDAO implements LocationDAORemote {

    /**
     * Default constructor. 
     */
	@PersistenceContext(unitName="IBeaconModel")
	EntityManager entityManager;
    public LocationDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Location addLocation(Location location) {
		// TODO Auto-generated method stub
		if(findBySectionFloors(location.getFloor(),location.getSection()) == null){
			entityManager.persist(location);
			entityManager.flush();
			location = findById(location.getIdLocation());
		} else {
			location = null;
		}
		return location ;
	}

	@Override
	public Location findById(int id) {
		return entityManager.find(Location.class, id);
	}
	public Location findBySectionFloors(int floor, int section) {
	
	 Location location = null;
	 Query q = entityManager.createQuery("SELECT l FROM Location l WHERE l.section = :section AND l.floor = :floor");
	 q.setParameter("floor", floor);
	 q.setParameter("section", section);
	 try{
		 
		 location= (Location) q.getSingleResult();
	   
	 }catch(Exception e){      
		 location =null;
	 }
	 return location;
	}
}
