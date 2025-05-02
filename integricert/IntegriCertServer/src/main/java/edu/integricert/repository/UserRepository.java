package edu.integricert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.integricert.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	// Since email is unique, we'll find users by email
	User findByEmail(String email);

	@Modifying(clearAutomatically = true)
	@Query("UPDATE User a SET a.role = :role WHERE a.id = :id")
	int updateRole(@Param("id") int id, @Param("role") String role);

	@Query("SELECT a FROM User a WHERE a.role = :role")
	List<User> findAllByRole(@Param("role") String role);
}
