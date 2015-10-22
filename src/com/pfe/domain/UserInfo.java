package com.pfe.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

public class UserInfo implements Serializable{

	public UserInfo() {
		
	}
	
	
	private String idUser;
	private String userFirstName;
	private String userLastName;
	private String userEmail;
	private List<Profile> userProfiles =new ArrayList<Profile>();
	private List<PrivateService> privateServices=new ArrayList<PrivateService>();
	private List<BDP> bdps = new ArrayList<BDP>();
	
	
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	
	
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	
	
	
	public List<Profile> getUserProfiles() {
		return  (userProfiles != null ? userProfiles : new ArrayList<Profile>());
	}
	public void setUserProfiles(List<Profile> userProfiles) {
		this.userProfiles = userProfiles;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	

	
	
	@Override
	public boolean equals(Object user) {
		// TODO Auto-generated method stub
		return (this.getIdUser().equals(((UserInfo)user).getIdUser()) ? true : false);
	}
	
	public List<PrivateService> getPrivateServices() {
		return privateServices;
	}
	public void setPrivateServices(List<PrivateService> privateServices) {
		this.privateServices = privateServices;
	}
}
