package edu.integricert.servie.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.integricert.model.College;
import edu.integricert.model.User;
import edu.integricert.repository.CollegeRepository;
import edu.integricert.repository.UserRepository;
import edu.integricert.request.CollegeRequest;
import edu.integricert.request.CollegeUpdateRequest;
import edu.integricert.service.CollegeService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CollegeServiceImpl implements CollegeService {
	private final CollegeRepository collegeRepository;
	private final UserRepository userRepo;
	
	@Override
	public void save(College college) {
		collegeRepository.save(college);
	}

	@Override
	public void saveAll(List<College> colleges) {
		collegeRepository.saveAll(colleges);
	}

	@Override
	public List<College> findAll() {
		return collegeRepository.findAll();
	}

	@Override
	public College findById(Integer id) {
		Optional<College> optional = collegeRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public College findByName(String name) {
		Optional<College> optional = collegeRepository.findByName(name);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public College createCollege(CollegeRequest collegeRequest) {
		String userEmail = collegeRequest.getUserEmail();
		String email = collegeRequest.getEmail();
		int establishedYear = collegeRequest.getEstablishedYear();
		String location = collegeRequest.getLocation();
		String mobile = collegeRequest.getMobile();
		String name = collegeRequest.getName();
		String registrationId = collegeRequest.getRegistrationId();

		User user = this.userRepo.findByEmail(userEmail);
		// validte user

		College college = new College();
		college.setName(name);
		college.setEmail(email);
		college.setEstablishedYear(establishedYear);
		college.setLocation(location);
		college.setMobile(mobile);
		college.setRegistrationId(registrationId);
		college.setCreatedAt(LocalDateTime.now());
		college.setCreatedBy(userEmail);

		collegeRepository.save(college);

		return college;
	}

	@Override
	public College updateCollege(CollegeUpdateRequest collegeRequest) {
		String userEmail = collegeRequest.getUserEmail();
		String email = collegeRequest.getEmail();
		int establishedYear = collegeRequest.getEstablishedYear();
		String location = collegeRequest.getLocation();
		String mobile = collegeRequest.getMobile();
		String name = collegeRequest.getName();
		String registrationId = collegeRequest.getRegistrationId();

		User user = this.userRepo.findByEmail(userEmail);
		// validte user

		Integer id = collegeRequest.getId();
		College college = findById(id);
		college.setName(name);
		college.setEmail(email);
		college.setEstablishedYear(establishedYear);
		college.setLocation(location);
		college.setMobile(mobile);
		college.setRegistrationId(registrationId);
		college.setModifiedAt(LocalDateTime.now());
		college.setModifiedBy(userEmail);

		collegeRepository.save(college);

		return college;
	}

	@Override
	public void deleteById(Integer id) {
		collegeRepository.deleteById(id);
	}
}
