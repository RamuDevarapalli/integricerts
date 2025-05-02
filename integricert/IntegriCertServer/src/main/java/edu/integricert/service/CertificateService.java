package edu.integricert.service;

import java.security.Principal;
import java.util.List;

import edu.integricert.model.Certificate;
import edu.integricert.model.Employee;
import edu.integricert.model.Student;
import edu.integricert.request.CertificateRequest;
import edu.integricert.request.CertificateUpdateRequest;

public interface CertificateService {

	Certificate createCertificate(CertificateRequest certificateRequest);

	List<Certificate> findAll();

	Certificate findById(Integer id);

	Certificate findByValue(String value);

	void save(Certificate certificate);

	void saveAll(List<Certificate> certificates);

	void deleteById(Integer id);

	Certificate updateCertificate(CertificateUpdateRequest certificateRequest);

	Certificate createCertificate(Student student, Principal principal);

	Certificate createCertificate(Employee employee, Principal principal);

}
