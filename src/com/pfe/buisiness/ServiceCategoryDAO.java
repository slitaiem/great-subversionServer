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
import com.pfe.domain.ServiceCategory;
import com.pfe.domain.User;

/**
 * Session Bean implementation class EnvironmentalDeviceServie
 */
@Stateless
@LocalBean
public class ServiceCategoryDAO implements ServiceCategoryDAORemote {

    /**
     * Default constructor. 
     */
	@PersistenceContext(unitName="IBeaconModel")
	EntityManager entityManager;
	
    public ServiceCategoryDAO() {
        // TODO Auto-generated constructor stub
    }


	@Override
	public ServiceCategory getServiceCategory(String categoryName) {
		ServiceCategory serviceCategory;
		Query q = entityManager.createQuery("SELECT b FROM ServiceCategory b WHERE b.categoryName = :UuId ");
	     q.setParameter("categoryName", categoryName);
	     try{
	    	 
	    	 serviceCategory = (ServiceCategory) q.getSingleResult();
	       
	     }catch(Exception e){      
	    	 serviceCategory =null;
	     }
		return serviceCategory;
	}
	


	

	@Override
	public void addServiceCategory(ServiceCategory serviceCategory) {
		if(getServiceCategory(serviceCategory.getCategoryName())== null){
			entityManager.persist(serviceCategory);
		}
		
	}

	@Override
	public List<ServiceCategory> getServiceCategories() {
		Query query = entityManager.createQuery("SELECT distinct b FROM ServiceCategory b  ");
		
		query.toString();
		//query.unwrap(org.hibernate.).getQueryString()
		List<ServiceCategory> serviceCategories = (List<ServiceCategory>) query.getResultList();
		return serviceCategories;
		
	}

}
