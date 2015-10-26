package com.pfe.buisiness;

import java.util.List;

import javax.ejb.Remote;

import com.pfe.domain.BDP;
import com.pfe.domain.EnvironmentalDevice;
import com.pfe.domain.Profile;
import com.pfe.domain.ServiceCategory;

@Remote
public interface ServiceCategoryDAORemote {

	void addServiceCategory(ServiceCategory serviceCategory);
	ServiceCategory getServiceCategory(String categoryName);
	
	List<ServiceCategory>  getServiceCategories();
}
