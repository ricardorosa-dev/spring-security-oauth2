package dev.ricardorosa.Validations.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinDTO {
	
	private String name;
	private LocalDate dateOfBirth;
	private String address;
	private String domain;

}
