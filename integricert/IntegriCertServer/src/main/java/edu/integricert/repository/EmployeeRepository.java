package edu.integricert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.integricert.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query(value = "SELECT c FROM Employee c WHERE c.company.id = :id")
	public List<Employee> findAllByCompany(@Param("id") Integer id);
	
	@Modifying
	@Query("UPDATE Employee c SET c.certificate = NULL WHERE c.certificate.id = :certificateId")
	void detachEmployeesFromCertificate(@Param("certificateId") Integer certificateId);
	
	@Query(value = "SELECT c FROM Employee c WHERE c.company.id = :companyId")
	public List<Employee> findAllEmployees(@Param("companyId") Integer companyId);
	
	@Query(value = "SELECT c FROM Employee c WHERE c.company.id = :companyId and c.certificate.id is NOT NULL")
	public List<Employee> findAllEmployeesWithCertificate(@Param("companyId") Integer companyId);

	@Modifying
	@Query(value = "DELETE FROM Employee c WHERE c.company.id = :companyId")
	public void deleteEmployeesByCompanyId(@Param("companyId") Integer companyId);
}
