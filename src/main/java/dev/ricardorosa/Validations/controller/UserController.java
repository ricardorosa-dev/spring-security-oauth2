package dev.ricardorosa.Validations.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.ricardorosa.Validations.dto.UserDTO;
import dev.ricardorosa.Validations.entity.Email;
import dev.ricardorosa.Validations.entity.User;
import dev.ricardorosa.Validations.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserService service;
	
	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> allUsers = service.findAll().stream()
				.map(this::toUserDTO)
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(allUsers, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id) {
		UserDTO user = this.toUserDTO(service.findById(id));
		
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> save(@RequestBody User newUser) {
		UserDTO user = this.toUserDTO(service.save(newUser));
		
		return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> update(
			@PathVariable("id") Long id, 
			@RequestBody User updateUser) {
		UserDTO user = this.toUserDTO(service.update(id, updateUser));
		
		return new ResponseEntity<UserDTO>(user, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		service.delete(id);
		
		return new ResponseEntity<String>("User deleted.", HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/name/{name}")
	public UserDTO findByName(@PathVariable("name") String name) {
		return this.toUserDTO(service.findByName(name));
	}
	
	@GetMapping("/namecontaining/{name}")
	public List<UserDTO> findByNameContaining(@PathVariable("name") String name) {
		return service.findByNameContaining(name).stream()
				.map(this::toUserDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/filter")
	public List<UserDTO> findByEmailAddress(
			@RequestParam("active") Boolean active, 
			Pageable pageable) {
		Page<User> page = service.findByActive(active, pageable);
		
		return page.getContent().stream()
				.map(this::toUserDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/starting/{prefix}")
	public List<UserDTO> findByNameStartingWith(@PathVariable("prefix") String prefix) {
		return service.findByNameStartingWith(prefix).stream()
				.map(this::toUserDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/ending/{suffix}")
	public List<UserDTO> findByNameEndingWith(@PathVariable("suffix") String suffix) {
		return service.findByNameEndingWith(suffix).stream()
				.map(this::toUserDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/names-with-j")
	public List<UserDTO> findNamesWithJ(Pageable pageable) {
		Page<User> page = service.findByNamesWithJ(pageable);
		
		return page.getContent().stream()
				.map(this::toUserDTO)
				.collect(Collectors.toList());
	}
	
	private UserDTO toUserDTO(User user) {
		UserDTO dto = new UserDTO();
		dto.setName(user.getName());
		dto.setActive(user.getActive());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		dto.setDateOfBirth(user.getDateOfBirth().format(formatter));
		
		List<String> userEmails = new ArrayList<>();
		for (Email email : user.getEmails()) {
			userEmails.add(email.getAddress());
		}
		
		dto.setEmails(userEmails);
		
		return dto;
	}

}
