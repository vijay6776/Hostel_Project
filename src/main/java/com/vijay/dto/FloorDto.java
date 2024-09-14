package com.vijay.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class FloorDto {
	
	
	private Long floorId;
	
	private Integer floorNumber;
	
	private List<RoomDto> roomsList;
	
}
