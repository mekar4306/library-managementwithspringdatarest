package com.karadayi.librarymanagement.entities;


import javax.persistence.Column;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false,nullable = false)
	private Long id;
	
	@Column(nullable = false) 
	private String name;
	
	
	public Long getResourceId() {
		
		return this.id;
	}
}
