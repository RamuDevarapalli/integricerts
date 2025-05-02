package edu.integricert.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.integricert.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

	@Query(value = "SELECT c FROM Company c WHERE c.name = :name")
	public Optional<Company> findByName(@Param("name") String name);
}
