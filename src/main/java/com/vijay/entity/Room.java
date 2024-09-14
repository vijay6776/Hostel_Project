package com.vijay.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roomId;
	
	private Integer roomNumber;
	
	private Integer sharingCount;
	
	private boolean isAirConditioned;
	
	@ManyToOne
	@JoinColumn(name = "floorId")
	
	private Floor floor;
	
	@OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
	private List<Person> persons; 

}
