package com.pfe.buisiness;

import java.util.Arrays;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.resteasy.util.IsAssignableFrom;

import com.pfe.domain.BDP;
import com.pfe.domain.EndTerminal;
import com.pfe.domain.EnvironmentalDevice;
import com.pfe.domain.Location;
import com.pfe.domain.PublicService;
import com.pfe.domain.User;

/**
 * Session Bean implementation class EnvironmentalDeviceServie
 */
@Stateless
@LocalBean
public class EnvironmentalDeviceDAO implements EnvironmentalDeviceDAORemote {

    /**
     * Default constructor. 
     */
	@PersistenceContext(unitName="IBeaconModel")
	EntityManager entityManager;
	
    public EnvironmentalDeviceDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addDevice(EnvironmentalDevice device) {
		if((device instanceof BDP && getBDPDevice(((BDP) device).getUuId())== null) 
				|| device instanceof EndTerminal){
			entityManager.persist(device);
		}
		
		
	}
	
	@Override
	public EnvironmentalDevice getBDPDevice(String UuId) {
		BDP bdp;
		Query q = entityManager.createQuery("SELECT b FROM BDP b WHERE b.UuId = :UuId ");
	     q.setParameter("UuId", UuId);
	     try{
	    	 
	    	 bdp = (BDP) q.getSingleResult();
	       
	     }catch(Exception e){      
	    	 bdp =null;
	     }
		return bdp;
	}
	
	@Override
	public EnvironmentalDevice getEndTerminaDevice(int deviceId) {
		
		EndTerminal device = entityManager.find(EndTerminal.class, deviceId);
		
		return device;
		
	}

	@Override
	public List<BDP> getBDPDevicesByLocationID(String[] idLocation) {
		Query query = entityManager.createQuery("SELECT bdp FROM BDP b  JOIN b.locations l"
				+ " WHERE  l.idLocation in :idLocation ");
		List<String> ids = Arrays.asList(idLocation);
		query.setParameter("idLocation", ids); 
		
		query.toString();
		//query.unwrap(org.hibernate.).getQueryString()
		List<BDP> bdps = (List<BDP>) query.getResultList();
		
		return bdps;
	}

}
