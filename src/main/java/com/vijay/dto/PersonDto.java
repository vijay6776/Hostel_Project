package com.vijay.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PersonDto {

	private Long personId;

	private String personName;

	private String personAge;

	private String personGender;

	private String personAadhaarNumber;

	private String personAddress;

	private LocalDateTime joiningDate;

	private LocalDate paymentDueDate;

	private boolean isPaidForTheMonth;

}
