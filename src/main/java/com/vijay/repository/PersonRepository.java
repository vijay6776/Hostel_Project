package com.vijay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vijay.entity.Person;

public interface PersonRepository extends JpaRepository<Person,Long> {
	
	@Query(value = "select * from person where room_id=:roomId",nativeQuery = true)
	List<Person> getAllPersonsOfRoom(Long roomId);
	
	List<Person> findByPersonName(String personName);

}
