package com.pfe.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="SERVICE")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="SERVICE_TYPE")
public class Service implements Serializable{

	public Service() {
		
	}
	
	
	private int idService;
	private String serviceName;
	private String serviceDescription;
	private List<Location> locations = new ArrayList<Location>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdService() {
		return idService;
	}
	public void setIdService(int idService) {
		this.idService = idService;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceDescription() {
		return serviceDescription;
	}
	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="SERVICE_LOCATION",joinColumns={@JoinColumn(name="SERVICE_FK")},
	 inverseJoinColumns={@JoinColumn(name="LOCATION_FK")})
	public List<Location> getLocations() {
		/*for (Location location : locations) {
			location.setServices(null);
		}*/
		return locations;
	}
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	public void addLocations(List<Location> locations) {
		for (Location location : locations) {
			if(!getLocations().contains(location)){
				getLocations().add(location);
			}
			
		}
		
	}
	
	
	@Override
	public boolean equals(Object service) {
		// TODO Auto-generated method stub
		return (this.getServiceName().equals(serviceName) );
	}
	
}
