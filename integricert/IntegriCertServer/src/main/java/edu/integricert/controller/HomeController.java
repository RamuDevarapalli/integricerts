package edu.integricert.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.integricert.model.Certificate;
import edu.integricert.model.College;
import edu.integricert.model.Company;
import edu.integricert.model.Employee;
import edu.integricert.model.Student;
import edu.integricert.service.HomeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {
	private final HomeService homeService;

	@GetMapping("/students")
	public ResponseEntity<List<Student>> findAllStudents() {
		return ResponseEntity.ok(homeService.findAllStudents());
	}

	@GetMapping("/student/{id}")
	public ResponseEntity<Student> findByIdStudent(@PathVariable Integer id) {
		return ResponseEntity.ok(homeService.findByIdStudent(id));
	}

	@GetMapping("/certificates")
	public ResponseEntity<List<Certificate>> findAllCertificates() {
		return ResponseEntity.ok(homeService.findAllCertificates());
	}

	@GetMapping("/certificate/{id}")
	public ResponseEntity<Certificate> findByIdCertificate(@PathVariable Integer id) {
		return ResponseEntity.ok(homeService.findByIdCertificate(id));
	}

	@GetMapping("/colleges")
	public ResponseEntity<List<College>> findAllColleges() {
		return ResponseEntity.ok(homeService.findAllColleges());
	}

	@GetMapping("/college/{id}")
	public ResponseEntity<College> findByIdCollege(@PathVariable Integer id) {
		return ResponseEntity.ok(homeService.findByIdCollege(id));
	}

	@DeleteMapping("/college/{id}")
	public ResponseEntity<Map<String, Object>> deleteByCollegeId(@PathVariable Integer id) {
		homeService.deleteByCollegeId(id);
		Map<String, Object> map = new HashMap<>();
		map.put("response", "Delete by college id");
		return ResponseEntity.ok(map);
	}

	@GetMapping("/companies")
	public ResponseEntity<List<Company>> findAllCompanies() {
		return ResponseEntity.ok(homeService.findAllCompanies());
	}

	@GetMapping("/company/{id}")
	public ResponseEntity<Company> findByIdCompany(@PathVariable Integer id) {
		return ResponseEntity.ok(homeService.findByIdCompany(id));
	}

	@DeleteMapping("/company/{id}")
	public ResponseEntity<Map<String, Object>> deleteByCompanyId(@PathVariable Integer id) {
		homeService.deleteByCompanyId(id);
		Map<String, Object> map = new HashMap<>();
		map.put("response", "Delete by company id");
		return ResponseEntity.ok(map);
	}

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> findAllEmployees() {
		return ResponseEntity.ok(homeService.findAllEmployees());
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> findByIdEmployee(@PathVariable Integer id) {
		return ResponseEntity.ok(homeService.findByIdEmployee(id));
	}

	@GetMapping
	public ResponseEntity<String> sayHello() {
		return ResponseEntity.ok("Hello Welcome to Integri Cert");
	}

	@GetMapping("/loadIntegriCertData")
	public ResponseEntity<String> loadPharmacyData(Principal principal) {
		String userEmail = principal.getName();
		homeService.loadIntegriCertData(userEmail);
		return ResponseEntity.ok("Load Integri Cert Data");
	}

}
