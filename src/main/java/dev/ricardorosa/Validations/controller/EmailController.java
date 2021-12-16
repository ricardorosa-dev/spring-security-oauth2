package dev.ricardorosa.Validations.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

import dev.ricardorosa.Validations.dto.EmailDTO;
import dev.ricardorosa.Validations.entity.Email;
import dev.ricardorosa.Validations.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {
	
	private EmailService service;
	
	@Autowired
	public EmailController(EmailService service) {
		this.service = service;
	}
	
	@GetMapping
	// método findAll com parâmetro opcional de nome
	public ResponseEntity<List<EmailDTO>> findAll(
			@RequestParam(required = false) String name
	) {
		List<EmailDTO> allEmails = new ArrayList<>();
		
		if (name == null) {
			allEmails = service.findAll().stream()
					.map(email -> this.toEmailDTO(email))
					.collect(Collectors.toList());
		} else {
			allEmails = service.findByUserName(name).stream()
					.map(this::toEmailDTO)
					.collect(Collectors.toList());
		}
		
		return new ResponseEntity<>(allEmails, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmailDTO> findById(@PathVariable("id") Long id) {
		EmailDTO email = this.toEmailDTO(service.findById(id));
		
		return new ResponseEntity<>(email, HttpStatus.OK);
	}
	
	// método de busca por múltiplos IDs
	@GetMapping("/search-by-id")
	public ResponseEntity<List<EmailDTO>> findByIdWithParams(
			@RequestParam List<Long> ids) {
		List<EmailDTO> emails = service.getByManyIds(ids).stream()
				.map(email -> toEmailDTO(email))
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(emails, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<EmailDTO> save(@RequestBody Email newEmail) {
		EmailDTO email = this.toEmailDTO(service.save(newEmail));
		
		return new ResponseEntity<>(email, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmailDTO> update(
			@PathVariable("id") Long id, 
			@RequestBody Email updateEmail) {
		EmailDTO email = this.toEmailDTO(service.update(id, updateEmail));
		
		return new ResponseEntity<>(email, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		service.delete(id);
		
		return new ResponseEntity<>("Email deleted.", HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/search")
	public List<EmailDTO> findByAddress(@RequestParam("address") String addressPart) {
		return service.findByAddressContaining(addressPart).stream()
				.map(this::toEmailDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/search/not/{userName}")
	public List<EmailDTO> findByUserNameNot(@PathVariable("userName") String userName) {
		return service.findByUserNameNot(userName).stream()
				.map(this::toEmailDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/top3")
	public EmailDTO findTop3() {
		return this.toEmailDTO(service.findTop3ByGbCapacity());
	}
	
	@GetMapping("/active")
	public List<EmailDTO> findActive() {
		return service.findByActiveTrue().stream()
				.map(this::toEmailDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/order")
	public List<EmailDTO> findByOrderBy() {
		return service.findByOrderByGbCapacity().stream()
				.map(this::toEmailDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/or/{address}/{username}")
	public List<EmailDTO> findByAddressOrUserName(
			@PathVariable("address") String address,
			@PathVariable("username") String userName
	) {
		return service.findByAddressOrUserName(address, userName).stream()
				.map(this::toEmailDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/like/{address}/{domain}")
	public List<EmailDTO> findByAddressLike(
			@PathVariable("address") String address,
			@PathVariable("domain") String domain
	) {
		return service.findByAddressLike(address, domain).stream()
				.map(this::toEmailDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/AoD/{address}/{domain}")
	public List<EmailDTO> findByAddressOrDomain(
			@PathVariable("address") String address,
			@PathVariable("domain") String domain) {
		return service.findByAddressOrDomain(address, domain).stream()
				.map(this::toEmailDTO)
				.collect(Collectors.toList());
	}
	
	EmailDTO toEmailDTO(Email email) {
		EmailDTO dto = new EmailDTO();
		dto.setAddress(email.getAddress());
		dto.setGbCapacity(email.getGbCapacity());
		dto.setDomain(email.getDomain());
		dto.setUser(email.getUser().getName());
		
		return dto;
	}

}
