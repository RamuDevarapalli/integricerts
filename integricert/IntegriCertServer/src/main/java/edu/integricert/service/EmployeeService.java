package edu.integricert.service;

import java.util.List;

import edu.integricert.model.Employee;
import edu.integricert.request.EmployeeRequest;
import edu.integricert.request.EmployeeUpdateRequest;

public interface EmployeeService {

	Employee createEmployee(EmployeeRequest employeeRequest);

	List<Employee> findAll();

	Employee findById(Integer id);

	void save(Employee employee);

	void saveAll(List<Employee> employees);

	void deleteById(Integer id);

	List<Employee> findAllByCompany(Integer id);

	Employee updateEmployee(EmployeeUpdateRequest employeeRequest);

	void detachEmployeesFromCertificate(Integer certificateId);

	List<Employee> findAllEmployees(Integer companyId);

	List<Employee> findAllEmployeesWithCertificate(Integer companyId);

	void deleteEmployeesByCompanyId(Integer companyId);
}
