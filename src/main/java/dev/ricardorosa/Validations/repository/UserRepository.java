package dev.ricardorosa.Validations.repository;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.ricardorosa.Validations.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByName(String name);
	
	List<User> findByNameContaining(String name);
	
	Page<User> findByActive(Boolean active, Pageable pageable);
	
	List<User> findByNameStartingWith(String prefix);
	
	List<User> findByNameEndingWith(String suffix);

}
