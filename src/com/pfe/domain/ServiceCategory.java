package com.pfe.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity


public class ServiceCategory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceCategory() {
		
	}
	
	
	private int idServiceCategory;
	private String categoryName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdServiceCategory() {
		return idServiceCategory;
	}
	public void setIdServiceCategory(int idServiceCategory) {
		this.idServiceCategory = idServiceCategory;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	

}
