package edu.integricert.servie.impl;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.integricert.model.Certificate;
import edu.integricert.model.Employee;
import edu.integricert.model.Student;
import edu.integricert.model.User;
import edu.integricert.model.constants.CertificateType;
import edu.integricert.repository.CertificateRepository;
import edu.integricert.repository.UserRepository;
import edu.integricert.request.CertificateRequest;
import edu.integricert.request.CertificateUpdateRequest;
import edu.integricert.service.CertificateService;
import edu.integricert.service.EmployeeService;
import edu.integricert.service.StudentService;
import edu.integricert.utils.BarcodeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {
	private final CertificateRepository certificateRepository;
	private final UserRepository userRepo;
	private final BarcodeService barcodeService;
	private final StudentService studentService;
	private final EmployeeService employeeService;

	@Override
	public void save(Certificate certificate) {
		certificateRepository.save(certificate);
	}

	@Override
	public void saveAll(List<Certificate> certificates) {
		certificateRepository.saveAll(certificates);
	}

	@Override
	public List<Certificate> findAll() {
		return certificateRepository.findAll();
	}

	@Override
	public Certificate findById(Integer id) {
		Optional<Certificate> optional = certificateRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public Certificate findByValue(String value) {
		Optional<Certificate> optional = certificateRepository.findByValue(value);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public Certificate createCertificate(Student student, Principal principal) {
		String userEmail = principal.getName();
		User user = this.userRepo.findByEmail(userEmail);
		// validte user

		CertificateRequest certificateRequest = new CertificateRequest();
		certificateRequest.setCertificateType(CertificateType.STUDENT);
		certificateRequest.setUserEmail(userEmail);

		try {
			String value = barcodeService.getStudentValue(student);
			certificateRequest.setValue(value);

			byte[] data = barcodeService.generateStudentCertificate(student, value);
			certificateRequest.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return createCertificate(certificateRequest);
	}

	@Override
	public Certificate createCertificate(Employee employee, Principal principal) {
		String userEmail = principal.getName();
		User user = this.userRepo.findByEmail(userEmail);
		// validte user

		CertificateRequest certificateRequest = new CertificateRequest();
		certificateRequest.setCertificateType(CertificateType.EMPLOYEE);
		certificateRequest.setUserEmail(userEmail);

		try {
			String value = barcodeService.getEmployeeValue(employee);
			certificateRequest.setValue(value);

			byte[] data = barcodeService.generateEmployeeCertificate(employee, value);
			certificateRequest.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return createCertificate(certificateRequest);
	}

	@Override
	public Certificate createCertificate(CertificateRequest certificateRequest) {
		String userEmail = certificateRequest.getUserEmail();
		String value = certificateRequest.getValue();
		CertificateType certificateType = certificateRequest.getCertificateType();
		byte[] data = certificateRequest.getData();

		User user = this.userRepo.findByEmail(userEmail);
		// validte user

		Certificate certificate = findByValue(value);
		if (certificate != null) {
			certificate.setValue(value);
			certificate.setCertificateType(certificateType);
			certificate.setData(data);
			certificate.setModifiedAt(LocalDateTime.now());
			certificate.setModifiedBy(userEmail);
		} else {
			certificate = new Certificate();
			certificate.setValue(value);
			certificate.setCertificateType(certificateType);
			certificate.setData(data);
			certificate.setCreatedAt(LocalDateTime.now());
			certificate.setCreatedBy(userEmail);
		}

		certificateRepository.save(certificate);

		return certificate;
	}

	@Override
	public Certificate updateCertificate(CertificateUpdateRequest certificateRequest) {
		String userEmail = certificateRequest.getUserEmail();
		String value = certificateRequest.getValue();
		CertificateType certificateType = certificateRequest.getCertificateType();
		byte[] data = certificateRequest.getData();

		User user = this.userRepo.findByEmail(userEmail);
		// validte user

		Integer id = certificateRequest.getId();
		Certificate certificate = findById(id);
		certificate.setValue(value);
		certificate.setCertificateType(certificateType);
		certificate.setData(data);
		certificate.setModifiedAt(LocalDateTime.now());
		certificate.setModifiedBy(userEmail);

		certificateRepository.save(certificate);

		return certificate;
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		Certificate certificate = findById(id);
		if (certificate != null) {
			CertificateType certificateType = certificate.getCertificateType();
			if (CertificateType.STUDENT.equals(certificateType)) {
				studentService.detachStudentsFromCertificate(id);
			} else if (CertificateType.EMPLOYEE.equals(certificateType)) {
				employeeService.detachEmployeesFromCertificate(id);
			}
		}
		certificateRepository.deleteById(id);
	}

}
