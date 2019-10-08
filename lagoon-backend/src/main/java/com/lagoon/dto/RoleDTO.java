package com.lagoon.dto;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;

import com.lagoon.model.RoleName;

public class RoleDTO {

	private Long id;
	private RoleNameDTO name;
	
	

	public RoleDTO() {
		this.id = 1L;
		this.name = new RoleDTO().name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleNameDTO getName() {
		return name;
	}

	public void setName(RoleNameDTO name) {
		this.name = name;
	}
}