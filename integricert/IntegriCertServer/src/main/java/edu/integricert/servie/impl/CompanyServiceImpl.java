package edu.integricert.servie.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.integricert.model.Company;
import edu.integricert.model.User;
import edu.integricert.repository.CompanyRepository;
import edu.integricert.repository.UserRepository;
import edu.integricert.request.CompanyRequest;
import edu.integricert.request.CompanyUpdateRequest;
import edu.integricert.service.CompanyService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
	private final CompanyRepository companyRepository;
	private final UserRepository userRepo;

	@Override
	public void save(Company company) {
		companyRepository.save(company);
	}

	@Override
	public void saveAll(List<Company> companys) {
		companyRepository.saveAll(companys);
	}

	@Override
	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	@Override
	public Company findById(Integer id) {
		Optional<Company> optional = companyRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public Company findByName(String name) {
		Optional<Company> optional = companyRepository.findByName(name);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public Company createCompany(CompanyRequest companyRequest) {
		String userEmail = companyRequest.getUserEmail();
		String email = companyRequest.getEmail();
		int establishedYear = companyRequest.getEstablishedYear();
		String location = companyRequest.getLocation();
		String mobile = companyRequest.getMobile();
		String name = companyRequest.getName();
		String registrationId = companyRequest.getRegistrationId();

		User user = this.userRepo.findByEmail(userEmail);
		// validte user

		Company company = new Company();
		company.setName(name);
		company.setEmail(email);
		company.setEstablishedYear(establishedYear);
		company.setLocation(location);
		company.setMobile(mobile);
		company.setRegistrationId(registrationId);
		company.setCreatedAt(LocalDateTime.now());
		company.setCreatedBy(userEmail);

		companyRepository.save(company);

		return company;
	}

	@Override
	public Company updateCompany(CompanyUpdateRequest companyRequest) {
		String userEmail = companyRequest.getUserEmail();
		String email = companyRequest.getEmail();
		int establishedYear = companyRequest.getEstablishedYear();
		String location = companyRequest.getLocation();
		String mobile = companyRequest.getMobile();
		String name = companyRequest.getName();
		String registrationId = companyRequest.getRegistrationId();

		User user = this.userRepo.findByEmail(userEmail);
		// validte user

		Integer id = companyRequest.getId();
		Company company = findById(id);
		company.setName(name);
		company.setEmail(email);
		company.setEstablishedYear(establishedYear);
		company.setLocation(location);
		company.setMobile(mobile);
		company.setRegistrationId(registrationId);
		company.setModifiedAt(LocalDateTime.now());
		company.setModifiedBy(userEmail);

		companyRepository.save(company);

		return company;
	}

	@Override
	public void deleteById(Integer id) {
		companyRepository.deleteById(id);
	}
}
