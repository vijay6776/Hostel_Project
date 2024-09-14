package com.vijay.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor 

public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long personId;
	
	private String personName;
	
	private String personAge;
	
	private String personGender;
	
	private String personAadhaarNumber;
	
	private String personAddress;
	
	private LocalDateTime joiningDate;
	
	private LocalDate paymentDueDate;
	
	private boolean isPaidForTheMonth;
	
	@ManyToOne
	@JoinColumn(name = "roomId")
	
	private Room room;

}
