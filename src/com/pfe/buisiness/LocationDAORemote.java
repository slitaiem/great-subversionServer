package com.pfe.buisiness;

import javax.ejb.Remote;

import com.pfe.domain.Location;

@Remote
public interface LocationDAORemote {

	Location addLocation(Location location);
    Location findById(int id);
	

}
