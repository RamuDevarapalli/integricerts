package edu.integricert.service;

import java.util.List;

import edu.integricert.model.College;
import edu.integricert.request.CollegeRequest;
import edu.integricert.request.CollegeUpdateRequest;

public interface CollegeService {

	College createCollege(CollegeRequest collegeRequest);

	List<College> findAll();

	College findById(Integer id);

	College findByName(String name);

	void save(College college);

	void saveAll(List<College> colleges);

	void deleteById(Integer id);

	College updateCollege(CollegeUpdateRequest collegeRequest);

}
