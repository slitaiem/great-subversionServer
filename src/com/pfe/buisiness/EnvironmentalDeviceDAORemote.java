package com.pfe.buisiness;

import java.util.List;

import javax.ejb.Remote;

import com.pfe.domain.BDP;
import com.pfe.domain.EnvironmentalDevice;
import com.pfe.domain.Profile;

@Remote
public interface EnvironmentalDeviceDAORemote {

	void addDevice(EnvironmentalDevice device);

	EnvironmentalDevice getEndTerminaDevice(int deviceId);

	EnvironmentalDevice getBDPDevice(String UuId,int idLocation);
	List<BDP>  getBDPDevicesByLocationID(String[]idLocation);
	List<BDP>  getAllBDPDevices();
	
}
