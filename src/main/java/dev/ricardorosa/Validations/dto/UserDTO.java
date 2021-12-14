package dev.ricardorosa.Validations.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	private String name;
	private Boolean active;
	private String dateOfBirth;
	private List<String> emails;

}
