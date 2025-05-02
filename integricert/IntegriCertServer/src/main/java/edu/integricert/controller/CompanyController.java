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

import edu.integricert.model.Company;
import edu.integricert.request.CompanyRequest;
import edu.integricert.request.CompanyUpdateRequest;
import edu.integricert.response.ApiResponse;
import edu.integricert.service.CompanyService;

@RestController
@CrossOrigin
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@GetMapping("/all")
	public ResponseEntity<List<Company>> companys() {
		return ResponseEntity.ok(companyService.findAll());
	}

	@PostMapping("/save")
	public ResponseEntity<Company> saveCompany(@RequestBody CompanyRequest request, Principal principal) {
		String userEmail = principal.getName();
		request.setUserEmail(userEmail);
		Company companyDto = this.companyService.createCompany(request);
		return new ResponseEntity<>(companyDto, HttpStatusCode.valueOf(200));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Company> getCompany(@PathVariable Integer id, Principal principal) {
		String userEmail = principal.getName();
		Company companyDto = this.companyService.findById(id);
		return new ResponseEntity<>(companyDto, HttpStatusCode.valueOf(200));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteCompany(Principal principal, @PathVariable Integer id) {
		String userEmail = principal.getName();
		this.companyService.deleteById(id);
		return new ResponseEntity<>(new ApiResponse("remove"), HttpStatusCode.valueOf(200));
	}

	@PostMapping("/update")
	public ResponseEntity<Company> updateCompany(@RequestBody CompanyUpdateRequest request, Principal principal) throws Exception {
		String userEmail = principal.getName();
		request.setUserEmail(userEmail);
		Company company = this.companyService.updateCompany(request);
		return new ResponseEntity<>(company, HttpStatusCode.valueOf(200));
	}
}
