package edu.integricert.service;

import java.util.List;

import edu.integricert.model.Certificate;
import edu.integricert.model.College;
import edu.integricert.model.Company;
import edu.integricert.model.Employee;
import edu.integricert.model.Student;

public interface HomeService {

	List<Student> findAllStudents();

	Student findByIdStudent(Integer id);

	List<Certificate> findAllCertificates();

	Certificate findByIdCertificate(Integer id);

	List<College> findAllColleges();

	College findByIdCollege(Integer id);

	List<Company> findAllCompanies();

	Company findByIdCompany(Integer id);

	List<Employee> findAllEmployees();

	Employee findByIdEmployee(Integer id);

	void deleteByCollegeId(Integer collegeId);

	void deleteByCompanyId(Integer companyId);

	void loadIntegriCertData(String userEmail);

}
