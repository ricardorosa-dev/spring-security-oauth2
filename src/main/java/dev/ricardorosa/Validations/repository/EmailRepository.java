package dev.ricardorosa.Validations.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.ricardorosa.Validations.entity.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long>{
	
	Optional<Email> findByAddress(String address);
	
	List<Email> findByAddressContaining(String address);
	
	List<Email> findByUserName(String userName);
	
	List<Email> findByUserNameNot(String userName);
	
	Email findTopByOrderByGbCapacity();
	
	List<Email> findTop2ByOrderByGbCapacityDesc();
	
	List<Email> findByUserActiveTrue();
	
	List<Email> findByAddressOrUserName(String address, String userName);
	
	List<Email> findByAddressLike(String pattern);
	
	@Query(value = ""
			+ "SELECT e FROM Email e "
			+ "WHERE UPPER(e.address) LIKE UPPER(CONCAT('%', :address, '%')) "
			+ "OR UPPER(e.domain) LIKE UPPER(CONCAT('%', :domain, '%'))"
			+ "ORDER BY e.gbCapacity DESC")
	List<Email> findByAddressOrDomain(String address, String domain);
	
	@Query(value = 
			"SELECT * FROM email "
			+ "WHERE email.id IN :ids",
			nativeQuery = true)
	List<Email> getByManyIds(List<Long> ids);

}
