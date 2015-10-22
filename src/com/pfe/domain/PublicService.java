package com.pfe.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
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

import com.pfe.domain.util.Priority;
@Entity
public class PublicService extends ServiceContent implements Serializable{

	public PublicService() {
		
	}
	
	private Priority priority;
	
	private List<Profile> profiles= new ArrayList<Profile>();
	
	public Priority getPriority() {
		return priority;
	}


	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	@ManyToMany
	@JoinTable(name="PUBLIC_SERVICE_PROFILE",joinColumns={@JoinColumn(name="PUBLIC_SERVICE_FK")},
	 inverseJoinColumns={@JoinColumn(name="PROFILE_FK")})
	public List<Profile> getProfiles() {
		
		return profiles;
	}


	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}
	
	public void addProfiles(List<Profile> profiles) {
		for (Profile profile : profiles) {
			if(!getProfiles().contains(profiles)){
				getProfiles().add(profile);
			}
			
		}
		
	}

	public PublicService deepCopy() {
		PublicService copy = new PublicService();
	    copy.setIdService(getIdService());
	    copy.setLocations(getLocations());
	    copy.setPriority(getPriority());
	    copy.setServiceDescription(getServiceDescription());
	    copy.setServiceName(getServiceName());
	    copy.setTextContent(getTextContent());
	    copy.setProfiles(null);
	    return copy;
	}

}
