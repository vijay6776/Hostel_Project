package com.vijay.dto;

import java.util.List;

import com.vijay.entity.Floor;
import com.vijay.entity.Person;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class RoomDto {
	
private Long roomId;
	
	private Integer roomNumber;
	
	private Integer sharingCount;
	
	private boolean isAirConditioned;
	
	private List<PersonDto> persons; 

}
