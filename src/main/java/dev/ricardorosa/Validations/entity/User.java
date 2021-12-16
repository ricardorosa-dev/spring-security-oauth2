package dev.ricardorosa.Validations.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import dev.ricardorosa.Validations.jackson.UserJsonDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = UserJsonDeserializer.class)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Boolean active;
	
	@Column(name = "date_of_birth")
	private LocalDate DateOfBirth;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Email> emails;
	
	@Override
	public String toString() {
		return "User [id=" + id + 
				", name=" + name + 
				", DateOfBirth=" + DateOfBirth + 
				", emails=" + emails + "]";
	}

}