package dev.ricardorosa.Validations.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import dev.ricardorosa.Validations.jackson.EmailJsonDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = EmailJsonDeserializer.class)
public class Email {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String address;
	private String password;
	@Column(name = "gb_capacity")
	private int gbCapacity;
	private String domain;
	
	@ManyToOne
	@JoinColumn(
			name = "user_id",
			referencedColumnName = "id"
	)
	private User user;

}
