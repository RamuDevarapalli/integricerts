package edu.integricert.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.integricert.model.College;

public interface CollegeRepository extends JpaRepository<College, Integer> {

	@Query(value = "SELECT c FROM College c WHERE c.name = :name")
	public Optional<College> findByName(@Param("name") String name);
}
