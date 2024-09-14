package com.vijay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vijay.dto.RoomDto;
import com.vijay.entity.Room;

public interface RoomRepository extends JpaRepository<Room,Long> {
	
	@Query(value = "select * from room where floor_id=:floorId",nativeQuery = true)
	List<Room> getAllRoomsOfFloor(Long floorId);

}
