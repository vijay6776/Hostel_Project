package com.vijay.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.dto.FloorDto;
import com.vijay.dto.PersonDto;
import com.vijay.dto.RoomDto;
import com.vijay.entity.Floor;
import com.vijay.entity.Person;
import com.vijay.entity.Room;
import com.vijay.service.HotelManagementService;

@RestController
@RequestMapping("/hotel")
@CrossOrigin("http://localhost:5173")
public class HotelManagementController {

	private HotelManagementService hotelManagementService;

	public HotelManagementController(HotelManagementService hotelManagementService) {
		super();
		this.hotelManagementService = hotelManagementService;
	}

	@PostMapping("/addfloor")
	public ResponseEntity<FloorDto> addFloor(@RequestBody Floor floor) {
		FloorDto addedFloor = hotelManagementService.addFloor(floor);

		return new ResponseEntity<>(addedFloor, HttpStatus.CREATED);
	}

	@PostMapping("/addfloors")
	public ResponseEntity<List<FloorDto>> addFloors(@RequestBody List<Floor> floorsList) {
		 List<FloorDto> addedFloorsList = hotelManagementService.addFloors(floorsList);
		return new ResponseEntity<List<FloorDto>>(addedFloorsList, HttpStatus.ACCEPTED);
	}

	@GetMapping("/getfloor/{floorId}")
	public ResponseEntity<FloorDto> getFloorDetails(@PathVariable Long floorId) {

		FloorDto floorDetails = hotelManagementService.getFloorDetails(floorId);

		return new ResponseEntity<FloorDto>(floorDetails, HttpStatus.OK);
	}

	@GetMapping("/getfloors")
	public ResponseEntity<List<FloorDto>> getAllFloorsDetails() {
		List<FloorDto> allFloorsDetails = hotelManagementService.getAllFloorsDetails();
		return new ResponseEntity<List<FloorDto>>(allFloorsDetails, HttpStatus.OK);
	}

	@PostMapping("/addroom")
	public ResponseEntity<RoomDto> addRoom(@RequestBody Room room) {
		 RoomDto addedRoom = hotelManagementService.addRoom(room);

		return new ResponseEntity<>(addedRoom, HttpStatus.ACCEPTED);
	}

	@PostMapping("/addrooms")
	public ResponseEntity<List<RoomDto>> addRooms(@RequestBody List<Room> roomsList) {
		 List<RoomDto> addedRooms = hotelManagementService.addRooms(roomsList);

		return new ResponseEntity<List<RoomDto>>(addedRooms, HttpStatus.ACCEPTED);
	}

	@GetMapping("/getroom/{roomId}")
	public ResponseEntity<RoomDto> getRoomDetails(@PathVariable Long roomId) {

		RoomDto roomDetails = hotelManagementService.getRoomDetails(roomId);

		return new ResponseEntity<RoomDto>(roomDetails, HttpStatus.OK);
	}

	@GetMapping("/getrooms")
	public ResponseEntity<List<RoomDto>> getAllRoomsDetails() {
	    List<RoomDto> allRoomsDetails= hotelManagementService.getAllRoomsDetails();
		return new ResponseEntity<List<RoomDto>>(allRoomsDetails, HttpStatus.OK);
	}

	@PostMapping("addperson")
	public ResponseEntity<PersonDto> addPersonToRoom(@RequestBody Person person) throws Exception {

		PersonDto addedPersonToRoom = hotelManagementService.addPersonToRoom(person);

		return new ResponseEntity<PersonDto>(addedPersonToRoom, HttpStatus.ACCEPTED);

	}

	@GetMapping("/getperson/{personId}")
	public ResponseEntity<PersonDto> getPersonDetails(@PathVariable Long personId) {

		 PersonDto personDetails = hotelManagementService.getPersonDetails(personId);

		return new ResponseEntity<PersonDto>(personDetails, HttpStatus.OK);

	}

	@GetMapping("/getpersons")
	public ResponseEntity<List<PersonDto>> getAllPersonsDetails() {

		List<PersonDto> allPersonsDetails = hotelManagementService.getAllPersonsDetails();

		return new ResponseEntity<List<PersonDto>>(allPersonsDetails, HttpStatus.OK);

	} 
//	******************************************
	@DeleteMapping("/deletefloor/{floorId}")
	public ResponseEntity<Boolean> deleteFloor(@PathVariable Long floorId) {
		
		boolean isDeleted = hotelManagementService.deleteFloor(floorId);
		return new ResponseEntity<Boolean>(isDeleted,HttpStatus.OK); 
		
	}
	
	@PutMapping("/updateroom/{roomId}")
	public ResponseEntity<RoomDto> updateRoom(@PathVariable Long roomId,@RequestBody RoomDto roomDto) {
		RoomDto updatedRoom = hotelManagementService.updateRoom(roomId, roomDto);
	    return new ResponseEntity<RoomDto>(updatedRoom,HttpStatus.OK); 
	}
	
	@DeleteMapping("/deleteroom/{roomId}")
	public ResponseEntity<Boolean> deleteRoom(@PathVariable Long roomId) {
		
		boolean isDeleted = hotelManagementService.deleteRoom(roomId);
		return new ResponseEntity<Boolean>(isDeleted,HttpStatus.OK); 
		
	}
	
	@DeleteMapping("/deleteperson/{personId}")
	public ResponseEntity<Boolean> deletePerson(@PathVariable Long personId) {
		boolean isDeleted = hotelManagementService.deleteRoom(personId);
		return new ResponseEntity<Boolean>(isDeleted,HttpStatus.OK);
	}
	
	@PutMapping("/updateperson/{personId}")
	public ResponseEntity<PersonDto> updatePerson(@PathVariable Long personId,@RequestBody PersonDto personDto) {
		PersonDto updatedPerson = hotelManagementService.updatePerson(personId, personDto);
	    return new ResponseEntity<PersonDto>(updatedPerson,HttpStatus.ACCEPTED);
	}

}
