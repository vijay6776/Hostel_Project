package com.vijay.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.vijay.dto.FloorDto;
import com.vijay.dto.PersonDto;
import com.vijay.dto.RoomDto;
import com.vijay.entity.Floor;
import com.vijay.entity.Person;
import com.vijay.entity.Room;
import com.vijay.repository.FloorRepository;
import com.vijay.repository.PersonRepository;
import com.vijay.repository.RoomRepository;

@Service
public class HotelManagementServiceImpl implements HotelManagementService {
	
	private FloorRepository floorRepository;
	
	private RoomRepository roomRepository;
	
	private PersonRepository personRepository;
	
	public HotelManagementServiceImpl(FloorRepository floorRepository, RoomRepository roomRepository,
			PersonRepository personRepository) {
		super();
		this.floorRepository = floorRepository;
		this.roomRepository = roomRepository;
		this.personRepository = personRepository;
	}

	@Override
	public FloorDto addFloor(Floor floor) {
		Floor savedFloor = floorRepository.save(floor);
		FloorDto floorDto=new FloorDto();
		
		BeanUtils.copyProperties(savedFloor, floorDto);
		
		return floorDto;
	}

	@Override
	public List<FloorDto> addFloors(List<Floor> floorsList) {
		List<Floor> savedFloorsList = floorRepository.saveAll(floorsList);
		List<FloorDto> savedFloorsDtoList = new ArrayList<>();
		savedFloorsList.stream().forEach(savedFloor->{
			FloorDto floorDto=new FloorDto();
			BeanUtils.copyProperties(savedFloor, floorDto);
			savedFloorsDtoList.add(floorDto);
		});
		return savedFloorsDtoList;
	}

	@Override
	public FloorDto getFloorDetails(Long floorId) {
		Optional<Floor> findById = floorRepository.findById(floorId);
		if(findById.isPresent()) {
			FloorDto floorDto=new FloorDto();
			BeanUtils.copyProperties(findById.get(), floorDto);
			floorDto.setRoomsList(getAllRoomDtosOfFloor(floorId));
			return floorDto;
		} else {
		
		return null;
		}
	}

	@Override
	public List<FloorDto> getAllFloorsDetails() {
		List<Floor> allFloorsList = floorRepository.findAll();
		List<FloorDto> allFloorsDtoList = new ArrayList<>();
		allFloorsList.stream().forEach(floor->{
			FloorDto floorDto=new FloorDto();
			floorDto.setRoomsList(getAllRoomDtosOfFloor(floor.getFloorId()));
			floorDto.getRoomsList().stream().forEach(room->{
				room.setPersons(getAllPersonDtosOfRoom(room.getRoomId()));
			});
			BeanUtils.copyProperties(floor, floorDto);
			allFloorsDtoList.add(floorDto);
		});
		return allFloorsDtoList;
	}

	@Override
	public RoomDto addRoom(Room room) {
		Room savedRoom = roomRepository.save(room);
		RoomDto roomDto=new RoomDto();
		BeanUtils.copyProperties(savedRoom, roomDto);
		return roomDto;
	}

	@Override
	public List<RoomDto> addRooms(List<Room> roomsList) {
		List<Room> savedRoomsList = roomRepository.saveAll(roomsList);
		List<RoomDto> savedRoomsDtoList=new ArrayList<>();
		savedRoomsList.stream().forEach(savedRoom->{
			RoomDto roomDto=new RoomDto();
			BeanUtils.copyProperties(savedRoom,roomDto);
			savedRoomsDtoList.add(roomDto);
		});
		return savedRoomsDtoList;
	}

	@Override
	public RoomDto getRoomDetails(Long roomId) {
		Optional<Room> findById = roomRepository.findById(roomId);
		if(findById.isPresent()) {
			RoomDto roomDto=new RoomDto();
			BeanUtils.copyProperties(findById.get(), roomDto);
			roomDto.setPersons(getAllPersonDtosOfRoom(roomId));
			return roomDto;
		}else {
		return null;
		}
	}

	@Override
	public List<RoomDto> getAllRoomsDetails() {
		List<Room> roomsList = roomRepository.findAll();
		List<RoomDto> roomDtos=new ArrayList<>();
		roomsList.stream().forEach(room->{
			RoomDto roomDto=new RoomDto();
			roomDto.setPersons(getAllPersonDtosOfRoom(room.getRoomId()));
			BeanUtils.copyProperties(room, roomDto);
			roomDtos.add(roomDto);
			
		});
		return roomDtos;
	}

	@Override
	public PersonDto addPersonToRoom(Person person) throws Exception {
		Optional<Room> findById = roomRepository.findById(person.getRoom().getRoomId());
		if(findById.isPresent() && ((findById.get().getPersons().size()+1)<=findById.get().getSharingCount())) {
			
		Person savedPerson = personRepository.save(person);
		PersonDto personDto=new PersonDto();
		BeanUtils.copyProperties(savedPerson, personDto);
		return personDto;
		}else {
			throw new Exception("Room Already Filled");
		}
	}

	@Override
	public PersonDto getPersonDetails(Long personId) {
		Optional<Person> findById = personRepository.findById(personId);
		if(findById.isPresent()) {
			PersonDto personDto=new PersonDto();
			BeanUtils.copyProperties(findById.get(), personDto);
			return personDto;
		} else {
		return null;
		}
	}

	@Override
	public List<PersonDto> getAllPersonsDetails() {
		List<Person> allPersonsList = personRepository.findAll();
		List<PersonDto> allPersonsDtosList=new ArrayList<>();
		allPersonsList.stream().forEach(person->{
			PersonDto personDto=new PersonDto();
			BeanUtils.copyProperties(person, personDto);
			allPersonsDtosList.add(personDto);
		});
		return allPersonsDtosList;
	}
	
//	***********************************************************************
	
	public List<RoomDto> getAllRoomDtosOfFloor(Long floorId){
		List<Room> roomsOfFloor = roomRepository.getAllRoomsOfFloor(floorId);
		List<RoomDto> roomDtosOfFloor=new ArrayList<>();
		roomsOfFloor.stream().forEach(room->{
		    RoomDto roomDto=new RoomDto();
		    BeanUtils.copyProperties(room, roomDto);
		    roomDtosOfFloor.add(roomDto);
		});
		return roomDtosOfFloor;
	}
	
	public List<PersonDto> getAllPersonDtosOfRoom(Long roomId){
		List<Person> allPersonsOfRoom = personRepository.getAllPersonsOfRoom(roomId);
		List<PersonDto> personDtos=new ArrayList<>();
		allPersonsOfRoom.stream().forEach(person->{
			PersonDto personDto=new PersonDto();
			BeanUtils.copyProperties(person, personDto);
			personDtos.add(personDto);
		});
		return personDtos;
	}

	@Override
	public boolean deleteFloor(Long floorId) {
		Optional<Floor> findById = floorRepository.findById(floorId);
		if(findById.isPresent()) {
			floorRepository.delete(findById.get());
			return true;
		}
		return false;
	}

	@Override
	public RoomDto updateRoom(Long roomId, RoomDto roomDto) {
		Optional<Room> findById = roomRepository.findById(roomId);
		if(findById.isPresent()) {
			Room room = findById.get();
			BeanUtils.copyProperties(roomDto,room);
			roomRepository.save(room);
			return roomDto;
			
		}
		return null;
	}

	@Override
	public boolean deleteRoom(Long roomId) {
		Optional<Room> findById = roomRepository.findById(roomId);
		if(findById.isPresent()) {
			roomRepository.delete(findById.get());
			return true;
		}
		return false;
	}

	@Override
	public boolean deletePerson(Long personId) {
		Optional<Person> findById = personRepository.findById(personId);
		if(findById.isPresent()) {
			personRepository.delete(findById.get());
			return true;
		}
		return false;
	}

	@Override
	public PersonDto updatePerson(Long personId, PersonDto personDto) {
		Optional<Person> findById = personRepository.findById(personId);
		if(findById.isPresent()) {
			Person person = findById.get();
			BeanUtils.copyProperties(personDto,person);
			personRepository.save(person);
			return personDto;
		}
		return null;
	}
	
	
}
