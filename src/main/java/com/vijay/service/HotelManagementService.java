package com.vijay.service;

import java.util.List;

import com.vijay.dto.FloorDto;
import com.vijay.dto.PersonDto;
import com.vijay.dto.RoomDto;
import com.vijay.entity.Floor;
import com.vijay.entity.Person;
import com.vijay.entity.Room;

public interface HotelManagementService {

	public FloorDto addFloor(Floor floor);

	public List<FloorDto> addFloors(List<Floor> floorsList);

	public FloorDto getFloorDetails(Long floorId);
	
	public List<FloorDto> getAllFloorsDetails();
	
	public RoomDto addRoom(Room room);

	public List<RoomDto> addRooms(List<Room> roomsList);
	
	public RoomDto getRoomDetails(Long roomId);
	
	public List<RoomDto> getAllRoomsDetails();

	public PersonDto addPersonToRoom(Person person) throws Exception;
	
	public PersonDto getPersonDetails(Long personId);
	
	public List<PersonDto> getAllPersonsDetails();
	
//	************************************************************************
	
	public boolean deleteFloor(Long floorId);
	
	public RoomDto updateRoom(Long roomId,RoomDto roomDto);
	
	public boolean deleteRoom(Long roomId);
	
	public boolean deletePerson(Long personId);
	
	public PersonDto updatePerson(Long personId,PersonDto personDto);
	

}
