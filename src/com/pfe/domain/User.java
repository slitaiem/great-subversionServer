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
@Entity
@Table(name="T_USER")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="USER_TYPE")
public class User implements Serializable{

	public User() {
		
	}
	
	
	private String idUser;
	private String userFirstName;
	private String userLastName;
	private Date userBirthDate;
	private List<Profile> userProfiles =new ArrayList<Profile>();
	private List<PrivateService> privateServices=new ArrayList<PrivateService>();
	private String userEmail;
	
	@Id
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
	public Date getUserBirthDate() {
		return userBirthDate;
	}
	public void setUserBirthDate(Date userBirthDate) {
		this.userBirthDate = userBirthDate;
	}
	
	
	public User( String userLastName,
			Date userBirthDate, String userFirstName) {
		super();
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userBirthDate = userBirthDate;
	}
	
	
	@ManyToMany(cascade ={CascadeType.ALL})
	@JoinTable(name="USER_PROFILE",joinColumns={@JoinColumn(name="USER_FK")},
			 inverseJoinColumns={@JoinColumn(name="PROFILE_FK")})
	
	public List<Profile> getUserProfiles() {
		return  userProfiles;
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
	

	public void addProfiles(List<Profile> profiles) {
		
		for (Profile profile : profiles) {
			if(!getUserProfiles().contains(profile)){
				getUserProfiles().add(profile);
			}
			if (!profile.getUsers().contains(this)) {
				profile.getUsers().add(this);
		     }
		} 
		
		
    }
	
	@Override
	public boolean equals(Object user) {
		// TODO Auto-generated method stub
		return (this.getIdUser().equals(((User)user).getIdUser()) ? true : false);
	}
	
	@ManyToMany(mappedBy="users")
	public List<PrivateService> getPrivateServices() {
		if(privateServices == null){
			privateServices = new ArrayList<PrivateService>();
		} 
		return privateServices;
	}
	public void setPrivateServices(List<PrivateService> privateServices) {
		this.privateServices = privateServices;
	}
	
	public UserInfo deepCopy(){
		UserInfo copy = new UserInfo();
	    copy.setIdUser(getIdUser());
	    copy.setPrivateServices(deepCopyPrivateServicesCollection());
	    copy.setUserProfiles(deepCopyProfilesCollection());
	    copy.setUserEmail(getUserEmail());
	    copy.setUserFirstName(getUserFirstName());
	    copy.setUserLastName(getUserLastName());
	    
	    return copy;
	}
	private List<Profile> deepCopyProfilesCollection() {
		List<Profile> copy = new ArrayList<Profile>();
		for (Profile profile : getUserProfiles()) {
			copy.add(profile.deepCopy());
		}
		return copy;
	}
	private List<PrivateService> deepCopyPrivateServicesCollection() {
		List<PrivateService> copy = new ArrayList<PrivateService>();
		for (PrivateService privateService : getPrivateServices()) {
			copy.add(privateService.deepCopy());
		}
		return copy;
	}
	
}
