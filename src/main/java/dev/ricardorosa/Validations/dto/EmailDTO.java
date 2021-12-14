package dev.ricardorosa.Validations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {
	
	private String address;
	private int gbCapacity;
	private String domain;
	private String user;

}
