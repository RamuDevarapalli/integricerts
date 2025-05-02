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

import edu.integricert.model.College;
import edu.integricert.request.CollegeRequest;
import edu.integricert.request.CollegeUpdateRequest;
import edu.integricert.response.ApiResponse;
import edu.integricert.service.CollegeService;

@RestController
@CrossOrigin
@RequestMapping("/college")
public class CollegeController {

	@Autowired
	private CollegeService collegeService;

	@GetMapping("/all")
	public ResponseEntity<List<College>> colleges() {
		return ResponseEntity.ok(collegeService.findAll());
	}

	@PostMapping("/save")
	public ResponseEntity<College> saveCollege(@RequestBody CollegeRequest request, Principal principal) {
		String userEmail = principal.getName();
		request.setUserEmail(userEmail);
		College collegeDto = this.collegeService.createCollege(request);
		return new ResponseEntity<>(collegeDto, HttpStatusCode.valueOf(200));
	}

	@GetMapping("/{id}")
	public ResponseEntity<College> getCollege(@PathVariable Integer id, Principal principal) {
		String userEmail = principal.getName();
		College collegeDto = this.collegeService.findById(id);
		return new ResponseEntity<>(collegeDto, HttpStatusCode.valueOf(200));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteCollege(Principal principal, @PathVariable Integer id) {
		String userEmail = principal.getName();
		this.collegeService.deleteById(id);
		return new ResponseEntity<>(new ApiResponse("remove"), HttpStatusCode.valueOf(200));
	}

	@PostMapping("/update")
	public ResponseEntity<College> updateCollege(@RequestBody CollegeUpdateRequest request, Principal principal) throws Exception {
		String userEmail = principal.getName();
		request.setUserEmail(userEmail);
		College college = this.collegeService.updateCollege(request);
		return new ResponseEntity<>(college, HttpStatusCode.valueOf(200));
	}
}
