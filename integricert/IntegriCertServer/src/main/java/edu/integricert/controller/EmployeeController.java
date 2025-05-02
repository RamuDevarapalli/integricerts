package edu.integricert.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.integricert.model.Certificate;
import edu.integricert.model.Employee;
import edu.integricert.request.EmployeeRequest;
import edu.integricert.request.EmployeeUpdateRequest;
import edu.integricert.response.ApiResponse;
import edu.integricert.service.CertificateService;
import edu.integricert.service.EmployeeService;

@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CertificateService certificateService;

	@GetMapping("/all")
	public ResponseEntity<List<Employee>> employees() {
		return ResponseEntity.ok(employeeService.findAll());
	}

	@PostMapping("/save")
	public ResponseEntity<Employee> saveEmployee(@RequestBody EmployeeRequest request, Principal principal) {
		String userEmail = principal.getName();
		request.setUserEmail(userEmail);

		Employee employee = this.employeeService.createEmployee(request);

		Certificate certificate = certificateService.createCertificate(employee, principal);
		employee.setCertificate(certificate);

		return new ResponseEntity<>(employee, HttpStatusCode.valueOf(200));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable Integer id, Principal principal) {
		String userEmail = principal.getName();
		Employee employeeDto = this.employeeService.findById(id);
		return new ResponseEntity<>(employeeDto, HttpStatusCode.valueOf(200));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable Integer id, Principal principal) {
		String userEmail = principal.getName();
		this.employeeService.deleteById(id);
		return new ResponseEntity<>(new ApiResponse("remove"), HttpStatusCode.valueOf(200));
	}

	@PostMapping("/update")
	public ResponseEntity<Employee> updateEmployee(@RequestBody EmployeeUpdateRequest request, Principal principal) throws Exception {
		String userEmail = principal.getName();
		request.setUserEmail(userEmail);
		Employee employee = this.employeeService.updateEmployee(request);
		return new ResponseEntity<>(employee, HttpStatusCode.valueOf(200));
	}

	@PostMapping("/generate/certificate")
	public ResponseEntity<Employee> generateEmployeeCertificate(@RequestBody EmployeeUpdateRequest request, Principal principal) throws Exception {
		String userEmail = principal.getName();
		request.setUserEmail(userEmail);

		Integer id = request.getId();
		Employee employee = this.employeeService.findById(id);
		if (employee != null) {
			Certificate certificate = certificateService.createCertificate(employee, principal);
			employee.setCertificate(certificate);
			employeeService.save(employee);
		}
		return new ResponseEntity<>(employee, HttpStatusCode.valueOf(200));
	}

	@PostMapping("/download/certificate")
	public ResponseEntity<byte[]> downloadEmployeeCertificate(@RequestBody EmployeeUpdateRequest request, Principal principal) throws Exception {
		String userEmail = principal.getName();
		request.setUserEmail(userEmail);

		Integer id = request.getId();
		Employee employee = this.employeeService.findById(id);
		if (employee != null) {
			Certificate certificate = employee.getCertificate();
			if (certificate != null) {
				byte[] bytes = certificate.getData();
				return ResponseEntity.ok().header("Content-Type", "application/pdf").body(bytes);
			}
		}
		return ResponseEntity.notFound().build();
	}
}
