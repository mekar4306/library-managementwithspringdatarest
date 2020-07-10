package com.karadayi.librarymanagement.entities;

import java.util.UUID;


import javax.persistence.Column;
import javax.persistence.Entity;


import org.hibernate.annotations.CreationTimestamp;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.OffsetDateTime;

import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Book extends AbstractEntity {
	
	private int pageCount;

	@Column(nullable = false)
	private UUID userUuid=UUID.randomUUID();	
	
	private boolean published;
	
	private OffsetDateTime publishedAt; 
	
	@CreationTimestamp
	@Column(updatable = false,nullable = false)
	private OffsetDateTime createdAt;//OffsetDateTime yerine Instant ve @CreationTimestamp kullansak nasil olur
	

	private String author;
	
	
}
