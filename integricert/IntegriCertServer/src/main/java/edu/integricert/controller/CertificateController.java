package edu.integricert.controller;

import java.security.Principal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.integricert.model.Certificate;
import edu.integricert.request.CertificateRequest;
import edu.integricert.request.CertificateUpdateRequest;
import edu.integricert.response.ApiResponse;
import edu.integricert.service.CertificateService;
import edu.integricert.utils.BarcodeService;

@RestController
@CrossOrigin
@RequestMapping("/certificate")
public class CertificateController {

	@Autowired
	private CertificateService certificateService;

	@Autowired
	private BarcodeService barcodeService;

	@GetMapping("/all")
	public ResponseEntity<List<Certificate>> certificates() {
		return ResponseEntity.ok(certificateService.findAll());
	}

	@PostMapping("/save")
	public ResponseEntity<Certificate> saveCertificate(@RequestBody CertificateRequest request, Principal principal) {
		String userEmail = principal.getName();
		request.setUserEmail(userEmail);
		Certificate certificateDto = this.certificateService.createCertificate(request);
		return new ResponseEntity<>(certificateDto, HttpStatusCode.valueOf(200));
	}

	@PostMapping("/validate")
	public ResponseEntity<?> validateCertificate(@RequestParam(name = "file", required = false) MultipartFile file, @RequestParam(name = "text", required = false) String text, Principal principal) {
		String userEmail = principal.getName();

		String code = null;
		if (file != null) {
			try {
				code = barcodeService.decodeBarcodeOrQRCode(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (StringUtils.isBlank(code) && StringUtils.isNotBlank(text)) {
			code = text;
		}

		if (StringUtils.isNotBlank(code)) {
			Certificate certificate = this.certificateService.findByValue(code);
			if (certificate != null) {
				return new ResponseEntity<>("Valid Certificate", HttpStatusCode.valueOf(200));
			}
		}
		return new ResponseEntity<>("Not a Valid Certificate", HttpStatusCode.valueOf(200));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Certificate> getCertificate(@PathVariable Integer id, Principal principal) {
		String userEmail = principal.getName();
		Certificate certificateDto = this.certificateService.findById(id);
		return new ResponseEntity<>(certificateDto, HttpStatusCode.valueOf(200));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteCertificate(Principal principal, @PathVariable Integer id) {
		String userEmail = principal.getName();
		this.certificateService.deleteById(id);
		return new ResponseEntity<>(new ApiResponse("remove"), HttpStatusCode.valueOf(200));
	}

	@PostMapping("/update")
	public ResponseEntity<Certificate> updateStudent(@RequestBody CertificateUpdateRequest request, Principal principal) throws Exception {
		String userEmail = principal.getName();
		request.setUserEmail(userEmail);
		Certificate certificate = this.certificateService.updateCertificate(request);
		return new ResponseEntity<>(certificate, HttpStatusCode.valueOf(200));
	}
}
