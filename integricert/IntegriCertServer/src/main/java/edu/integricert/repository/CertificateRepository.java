package edu.integricert.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.integricert.model.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Integer> {

	@Query(value = "SELECT c FROM Certificate c WHERE c.value = :value")
	public Optional<Certificate> findByValue(@Param("value") String value);
}
