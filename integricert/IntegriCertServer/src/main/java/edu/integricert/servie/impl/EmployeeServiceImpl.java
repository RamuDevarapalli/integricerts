package edu.integricert.servie.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.integricert.model.Certificate;
import edu.integricert.model.Company;
import edu.integricert.model.Employee;
import edu.integricert.repository.EmployeeRepository;
import edu.integricert.repository.UserRepository;
import edu.integricert.request.EmployeeRequest;
import edu.integricert.request.EmployeeUpdateRequest;
import edu.integricert.service.CompanyService;
import edu.integricert.service.EmployeeService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	private final EmployeeRepository employeeRepository;
	private final UserRepository userRepo;
	private final CompanyService companyService;

	@Override
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public void saveAll(List<Employee> employees) {
		employeeRepository.saveAll(employees);
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(Integer id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<Employee> findAllByCompany(Integer id) {
		return employeeRepository.findAllByCompany(id);
	}

	@Override
	public Employee createEmployee(EmployeeRequest employeeRequest) {
		String userEmail = employeeRequest.getUserEmail();
		String name = employeeRequest.getName();
		String gender = employeeRequest.getGender();
		String joiningDate = employeeRequest.getJoiningDate();
		String companyName = employeeRequest.getCompanyName();
		String designation = employeeRequest.getDesignation();
		String department = employeeRequest.getDepartment();
		String email = employeeRequest.getEmail();
		String mobile = employeeRequest.getMobile();
		String address = employeeRequest.getAddress();
		String dob = employeeRequest.getDob();
		Certificate certificate = employeeRequest.getCertificate();

//		User user = this.userRepo.findByEmail(userEmail);
		Company company = companyService.findByName(companyName);
				
		Employee employee = new Employee();
		employee.setName(name);
		employee.setGender(gender);
		employee.setJoiningDate(joiningDate);
		employee.setCompany(company);
		employee.setEmail(email);
		employee.setMobile(mobile);
		employee.setAddress(address);
		employee.setDesignation(designation);
		employee.setDepartment(department);
		employee.setDob(dob);
		employee.setCertificate(certificate);
		employee.setCreatedAt(LocalDateTime.now());
		employee.setCreatedBy(userEmail);

		employeeRepository.save(employee);

		return employee;
	}

	@Override
	public Employee updateEmployee(EmployeeUpdateRequest employeeRequest) {
		String userEmail = employeeRequest.getUserEmail();
		String name = employeeRequest.getName();
		String gender = employeeRequest.getGender();
		String joiningDate = employeeRequest.getJoiningDate();
		Company company = employeeRequest.getCompany();
		String designation = employeeRequest.getDesignation();
		String department = employeeRequest.getDepartment();
		String email = employeeRequest.getEmail();
		String mobile = employeeRequest.getMobile();
		String address = employeeRequest.getAddress();
		String dob = employeeRequest.getDob();
		Certificate certificate = employeeRequest.getCertificate();

//		User user = this.userRepo.findByEmail(userEmail);

		Integer id = employeeRequest.getId();
		Employee employee = findById(id);
		employee.setName(name);
		employee.setGender(gender);
		employee.setJoiningDate(joiningDate);
		employee.setCompany(company);
		employee.setEmail(email);
		employee.setMobile(mobile);
		employee.setAddress(address);
		employee.setDesignation(designation);
		employee.setDepartment(department);
		employee.setDob(dob);
		employee.setCertificate(certificate);
		employee.setModifiedAt(LocalDateTime.now());
		employee.setModifiedBy(userEmail);

		employeeRepository.save(employee);

		return employee;
	}

	@Override
	public void deleteById(Integer id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public void detachEmployeesFromCertificate(Integer certificateId) {
		employeeRepository.detachEmployeesFromCertificate(certificateId);
	}
	
	@Override
	public void deleteEmployeesByCompanyId(Integer companyId) {
		employeeRepository.deleteEmployeesByCompanyId(companyId);
	}
	
	@Override
	public List<Employee> findAllEmployees(Integer companyId) {
		return employeeRepository.findAllEmployees(companyId);
	}

	@Override
	public List<Employee> findAllEmployeesWithCertificate(Integer companyId) {
		return employeeRepository.findAllEmployeesWithCertificate(companyId);
	}
}
