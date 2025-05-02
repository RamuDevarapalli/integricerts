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
import edu.integricert.model.Student;
import edu.integricert.request.StudentRequest;
import edu.integricert.request.StudentUpdateRequest;
import edu.integricert.response.ApiResponse;
import edu.integricert.service.CertificateService;
import edu.integricert.service.StudentService;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private CertificateService certificateService;

	@GetMapping("/all")
	public ResponseEntity<List<Student>> students() {
		return ResponseEntity.ok(studentService.findAll());
	}

	@PostMapping("/save")
	public ResponseEntity<Student> saveStudent(@RequestBody StudentRequest request, Principal principal) {
		String userEmail = principal.getName();
		request.setUserEmail(userEmail);

		Student student = this.studentService.createStudent(request);

		Certificate certificate = certificateService.createCertificate(student, principal);
		student.setCertificate(certificate);

		return new ResponseEntity<>(student, HttpStatusCode.valueOf(200));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable Integer id, Principal principal) {
		String userEmail = principal.getName();
		Student studentDto = this.studentService.findById(id);
		return new ResponseEntity<>(studentDto, HttpStatusCode.valueOf(200));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteStudent(@PathVariable Integer id, Principal principal) {
		String userEmail = principal.getName();
		this.studentService.deleteById(id);
		return new ResponseEntity<>(new ApiResponse("remove"), HttpStatusCode.valueOf(200));
	}

	@PostMapping("/update")
	public ResponseEntity<Student> updateStudent(@RequestBody StudentUpdateRequest request, Principal principal) throws Exception {
		String userEmail = principal.getName();
		request.setUserEmail(userEmail);
		Student student = this.studentService.updateStudent(request);
		return new ResponseEntity<>(student, HttpStatusCode.valueOf(200));
	}

	@PostMapping("/generate/certificate")
	public ResponseEntity<Student> generateStudentCertificate(@RequestBody StudentUpdateRequest request, Principal principal) throws Exception {
		String userEmail = principal.getName();
		request.setUserEmail(userEmail);

		Integer id = request.getId();
		Student student = this.studentService.findById(id);
		if (student != null) {
			Certificate certificate = certificateService.createCertificate(student, principal);
			student.setCertificate(certificate);
			studentService.save(student);
		}
		return new ResponseEntity<>(student, HttpStatusCode.valueOf(200));
	}

	@PostMapping("/download/certificate")
	public ResponseEntity<byte[]> downloadStudentCertificate(@RequestBody StudentUpdateRequest request, Principal principal) throws Exception {
		String userEmail = principal.getName();
		request.setUserEmail(userEmail);

		Integer id = request.getId();
		Student student = this.studentService.findById(id);
		if (student != null) {
			Certificate certificate = student.getCertificate();
			if (certificate != null) {
				byte[] bytes = certificate.getData();
				return ResponseEntity.ok().header("Content-Type", "application/pdf").body(bytes);
			}
		}
		return ResponseEntity.notFound().build();
	}
}
