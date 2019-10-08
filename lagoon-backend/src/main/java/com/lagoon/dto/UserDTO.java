package com.lagoon.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.lagoon.model.Photo;
import com.lagoon.model.Role;
import com.opencsv.bean.CsvBindByName;

public class UserDTO {

	private Long userId;
	@NotBlank(message="First Name cannot be empty")
	@CsvBindByName(column = "productCode")
	private String firstName;
	
	@NotBlank(message="Last Name cannot be empty")
	private String lastName;
	
	@NotBlank(message="UserName is required")
	private String userName;
	
	@Length(max=16, min=8)
	private String password;
	private Date created;
	private List<Photo> photoList;
	private List<Photo> likedPhotoList;
	private Set<Role> roles;

	public UserDTO() {
		this.userId = 1l;
		this.firstName = "";
		this.lastName = "";
		this.userName = "";
		this.password = "";
		this.created = new Date();
		this.photoList = new ArrayList<Photo>();
		this.likedPhotoList = new ArrayList<Photo>();
		this.roles = new HashSet<Role>();
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public List<Photo> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(List<Photo> photoList) {
		this.photoList = photoList;
	}

	public List<Photo> getLikedPhotoList() {
		return likedPhotoList;
	}

	public void setLikedPhotoList(List<Photo> likedPhotoList) {
		this.likedPhotoList = likedPhotoList;
	}
}