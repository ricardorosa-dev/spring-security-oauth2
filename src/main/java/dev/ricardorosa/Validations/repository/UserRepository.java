package dev.ricardorosa.Validations.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.ricardorosa.Validations.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByName(String name);
	
	List<User> findByNameContaining(String name);
	
	Page<User> findByActive(Boolean active, Pageable pageable);
	
	List<User> findByNameStartingWith(String prefix);
	
	List<User> findByNameEndingWith(String suffix);
	
	@Query(value = 
		"SELECT * FROM user "
		+ "WHERE UPPER(user.name) LIKE UPPER('j%')",
		countQuery = "SELECT COUNT(*) FROM user",
		nativeQuery = true)
	Page<User> findNamesWithJ(Pageable pageable);

}
