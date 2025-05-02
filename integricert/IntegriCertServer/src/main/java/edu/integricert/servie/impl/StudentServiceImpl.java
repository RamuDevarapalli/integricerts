package edu.integricert.servie.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.integricert.model.Certificate;
import edu.integricert.model.College;
import edu.integricert.model.Student;
import edu.integricert.model.User;
import edu.integricert.repository.StudentRepository;
import edu.integricert.repository.UserRepository;
import edu.integricert.request.StudentRequest;
import edu.integricert.request.StudentUpdateRequest;
import edu.integricert.service.CollegeService;
import edu.integricert.service.StudentService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
	private final StudentRepository studentRepository;
	private final UserRepository userRepo;
	private final CollegeService collegeService;

	@Override
	public void save(Student student) {
		studentRepository.save(student);
	}

	@Override
	public void saveAll(List<Student> students) {
		studentRepository.saveAll(students);
	}

	@Override
	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	@Override
	public Student findById(Integer id) {
		Optional<Student> optional = studentRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<Student> findAllByCollege(Integer id) {
		return studentRepository.findAllByCollege(id);
	}

	@Override
	public Student createStudent(StudentRequest studentRequest) {
		String userEmail = studentRequest.getUserEmail();
		String name = studentRequest.getName();
		String gender = studentRequest.getGender();
		String collegeName = studentRequest.getCollegeName();
		String course = studentRequest.getCourse();
		String email = studentRequest.getEmail();
		String mobile = studentRequest.getMobile();
		String address = studentRequest.getAddress();
		String academicYear = studentRequest.getAcademicYear();
		String courseResult = studentRequest.getCourseResult();
		String degree = studentRequest.getDegree();
		String dob = studentRequest.getDob();
		Certificate certificate = studentRequest.getCertificate();

		User user = this.userRepo.findByEmail(userEmail);
		College college = this.collegeService.findByName(collegeName);

		Student student = new Student();
		student.setName(name);
		student.setGender(gender);
		student.setCollege(college);
		student.setCourse(course);
		student.setEmail(email);
		student.setMobile(mobile);
		student.setAddress(address);
		student.setAcademicYear(academicYear);
		student.setCourseResult(courseResult);
		student.setDegree(degree);
		student.setDob(dob);
		student.setCertificate(certificate);
		student.setCreatedAt(LocalDateTime.now());
		student.setCreatedBy(userEmail);

		studentRepository.save(student);

		return student;
	}

	@Override
	public Student updateStudent(StudentUpdateRequest studentRequest) {
		String userEmail = studentRequest.getUserEmail();
		String name = studentRequest.getName();
		String gender = studentRequest.getGender();
		College college = studentRequest.getCollege();
		String course = studentRequest.getCourse();
		String email = studentRequest.getEmail();
		String mobile = studentRequest.getMobile();
		String address = studentRequest.getAddress();
		String academicYear = studentRequest.getAcademicYear();
		String courseResult = studentRequest.getCourseResult();
		String degree = studentRequest.getDegree();
		String dob = studentRequest.getDob();
		Certificate certificate = studentRequest.getCertificate();

//		User user = this.userRepo.findByEmail(userEmail);
//		College college = this.collegeService.findById(collegeId);

		Integer id = studentRequest.getId();
		Student student = findById(id);
		student.setName(name);
		student.setGender(gender);
		student.setCollege(college);
		student.setCourse(course);
		student.setEmail(email);
		student.setMobile(mobile);
		student.setAddress(address);
		student.setAcademicYear(academicYear);
		student.setCourseResult(courseResult);
		student.setDegree(degree);
		student.setDob(dob);
		student.setCertificate(certificate);
		student.setModifiedAt(LocalDateTime.now());
		student.setModifiedBy(userEmail);

		studentRepository.save(student);

		return student;
	}

	@Override
	public void deleteById(Integer id) {
		studentRepository.deleteById(id);
	}

	@Override
	public void detachStudentsFromCertificate(Integer certificateId) {
		studentRepository.detachStudentsFromCertificate(certificateId);
	}

	@Override
	public void deleteStudentsByCollegeId(Integer collegeId) {
		studentRepository.deleteStudentsByCollegeId(collegeId);
	}

	@Override
	public List<Student> findAllStudents(Integer collegeId) {
		return studentRepository.findAllStudents(collegeId);
	}

	@Override
	public List<Student> findAllStudentsWithCertificate(Integer collegeId) {
		return studentRepository.findAllStudentsWithCertificate(collegeId);
	}

}
