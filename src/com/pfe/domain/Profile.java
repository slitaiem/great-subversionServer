package com.pfe.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
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
public class Profile  implements Serializable{

	
	
	public Profile() {
		
	}
	
	
	private int profileId;
	private String profileName;
	private boolean isPublic;
	private List<User> users =new ArrayList<User>();
	private List<PublicService> publicServices;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getProfileId() {
		return profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	};
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	
	 @ManyToMany(mappedBy="userProfiles")
	public List<User> getUsers() {
		
		return (users != null ? users : new ArrayList<User>());
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	@Column(name="visible_by_guests")
	public boolean isPublic() {
		return isPublic;
	}
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	
	@ManyToMany(mappedBy="profiles")
	public List<PublicService> getPublicServices() {
		
		return publicServices;
	}
	public void setPublicServices(List<PublicService> publicServices) {
		this.publicServices = publicServices;
	}
	
	
	public void addUsers(List<User> users) {
		
		for (User user : users) {
			if(!getUsers().contains(user)){
				getUsers().add(user);
			}
			if (!user.getUserProfiles().contains(this)) {
				user.getUserProfiles().add(this);
		     }

		}
		
    }
	
	@Override
	public boolean equals(Object profile) {
		// TODO Auto-generated method stub
		
		
		return (this.getProfileName().equals(((Profile)profile).getProfileName() )? true : false);
	}
	
	public Profile deepCopy(){
		Profile copy = new Profile();
	    copy.setProfileId(getProfileId());
	    copy.setProfileName(getProfileName());
	    copy.setUsers(null);
	    copy.setPublicServices(deepCopyPublicServicesCollection());
	    copy.setUsers(null);
	    return copy;
	}
	private List<PublicService> deepCopyPublicServicesCollection() {
		List<PublicService> copy = new ArrayList<PublicService>();
		for (PublicService publicService : getPublicServices()) {
			copy.add(publicService.deepCopy());
		}
		return copy;
	}
}
