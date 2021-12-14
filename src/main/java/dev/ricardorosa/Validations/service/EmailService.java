package dev.ricardorosa.Validations.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.ricardorosa.Validations.entity.Email;
import dev.ricardorosa.Validations.exceptions.AlreadyExistsException;
import dev.ricardorosa.Validations.exceptions.IncompleteBodyException;
import dev.ricardorosa.Validations.exceptions.NotFoundException;
import dev.ricardorosa.Validations.exceptions.WrongEmailFormatException;
import dev.ricardorosa.Validations.repository.EmailRepository;

@Service
public class EmailService {
	
private final EmailRepository repository;
	
	@Autowired
	public EmailService(EmailRepository repository) {
		this.repository = repository;
	}
	
	public List<Email> findAll() {
		return repository.findAll();
	}
	
	public Email findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new NotFoundException("email", "id", id.toString()));
	}
	
	public Email save(Email newEmail) {
		if (newEmail.getAddress() == null
			|| newEmail.getPassword() == null
			|| newEmail.getGbCapacity() == 0
			|| newEmail.getDomain() == null
			|| newEmail.getUser() == null) {
			throw new IncompleteBodyException("email", "'address', 'password', 'gbCapacity', 'domain' and 'user'");
		}
		
		Pattern emailPattern = Pattern.compile(
				"[\\w]+@[\\w]+.[\\w]{2,4}(.[\\w]{2,3})?", 
				Pattern.CASE_INSENSITIVE);
		Matcher emailMatcher = emailPattern.matcher(newEmail.getAddress());
		if (emailMatcher.find() == false) {
			throw new WrongEmailFormatException();
		}
		
		Email exists = repository.findByAddress(newEmail.getAddress()).orElse(null);
		if (exists != null) {
			throw new AlreadyExistsException("email", "address", exists.getAddress());
		}
		
		return repository.save(newEmail);
	}
		
	public Email update(Long id, Email updateEmail) {
		if (updateEmail.getAddress() == null
			|| updateEmail.getPassword() == null
			|| updateEmail.getGbCapacity() == 0
			|| updateEmail.getDomain() == null
			|| updateEmail.getUser() == null) {
			throw new IncompleteBodyException("email", "'address', 'password', 'gbCapacity', 'domain' and 'user'");
		}
		
		Email foundEmail = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("email", "id", id.toString()));
		
		foundEmail.setAddress(updateEmail.getAddress());
		foundEmail.setPassword(updateEmail.getPassword());
		foundEmail.setGbCapacity(updateEmail.getGbCapacity());
		foundEmail.setDomain(updateEmail.getDomain());
		foundEmail.setUser(updateEmail.getUser());
		
		return repository.save(foundEmail);
	}
	
	public void delete(Long id) {
		Email foundEmail = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("email", "id", id.toString()));
		
		repository.delete(foundEmail);
	}
	
	public List<Email> findByAddressContaining(String address) {
		return repository.findByAddressContaining(address);
	}
	
	public List<Email> findByUserName(String userName) {
		return repository.findByUserName(userName);
	}
	
	public List<Email> findByUserNameNot(String userName) {
		return repository.findByUserNameNot(userName);
	}
	
	public Email findTop3ByGbCapacity() {
		return repository.findTopByOrderByGbCapacity();
	}
	
	public List<Email> findByOrderByGbCapacity() {
		return repository.findTop2ByOrderByGbCapacityDesc();
	}
	
	public List<Email> findByActiveTrue() {
		return repository.findByUserActiveTrue();
	}
	
	public List<Email> findByAddressOrUserName(String address, String userName) {
		return repository.findByAddressOrUserName(address, userName);
	}
	
	public List<Email> findByAddressLike(String address, String domain) {
		String likePattern = address + "%@%" + domain; 
		return repository.findByAddressLike(likePattern);
	}
	
	public List<Email> getByManyIds(List<Long> ids) {
		return repository.getByManyIds(ids);
	}
	
	public List<Email> findByAddressOrDomain(String address, String domain) {
		return repository.findByAddressOrDomain(address, domain);
	}

}
