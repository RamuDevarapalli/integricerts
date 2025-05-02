package edu.integricert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.integricert.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	@Query(value = "SELECT c FROM Student c WHERE c.college.id = :id")
	public List<Student> findAllByCollege(@Param("id") Integer id);
	
	@Modifying
	@Query("UPDATE Student c SET c.certificate = NULL WHERE c.certificate.id = :certificateId")
	void detachStudentsFromCertificate(@Param("certificateId") Integer certificateId);
	
	@Query(value = "SELECT c FROM Student c WHERE c.college.id = :collegeId")
	public List<Student> findAllStudents(@Param("collegeId") Integer collegeId);
	
	@Query(value = "SELECT c FROM Student c WHERE c.college.id = :collegeId and c.certificate.id is NOT NULL")
	public List<Student> findAllStudentsWithCertificate(@Param("collegeId") Integer collegeId);

	@Modifying
	@Query(value = "DELETE FROM Student c WHERE c.college.id = :collegeId")
	public void deleteStudentsByCollegeId(@Param("collegeId") Integer collegeId);
}
