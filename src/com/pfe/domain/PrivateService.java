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

import com.pfe.domain.util.DataTypeSent;
@Entity
public class PrivateService extends ServiceContent implements Serializable{

	private List<User> users = new ArrayList<User>();
	private DataTypeSent dataTypeSent ;

	public PrivateService() {
		
	}
	
	public DataTypeSent getDataTypeSent() {
		return dataTypeSent;
	}

	public void setDataTypeSent(DataTypeSent dataTypeSent) {
		this.dataTypeSent = dataTypeSent;
	}
	
	
	
	public void addUsers(List<User> users) {
		
		for (User user : users) {
			if(!getUsers().contains(user)){
				getUsers().add(user);
			}
		}
		
	}
	@ManyToMany
	@JoinTable(name="PRIVATE_SERVICE_USER",joinColumns={@JoinColumn(name="PRIVATE_SERVICE_FK")},
	 inverseJoinColumns={@JoinColumn(name="SERVICE_FK")})
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
	public PrivateService deepCopy(){
		PrivateService copy = new PrivateService();
	    copy.setIdService(getIdService());
	    copy.setDataTypeSent(getDataTypeSent());
	    copy.setServiceDescription(getServiceDescription());
	    copy.setServiceName(getServiceName());
	    copy.setTextContent(getTextContent());
	    copy.setLocations(getLocations());
	    copy.setUsers(null);
	    return copy;
	}
}
