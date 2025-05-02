package edu.integricert.service;

import java.util.List;

import edu.integricert.model.Student;
import edu.integricert.request.StudentRequest;
import edu.integricert.request.StudentUpdateRequest;

public interface StudentService {

	Student createStudent(StudentRequest studentRequest);

	List<Student> findAll();

	Student findById(Integer id);

	void save(Student student);

	void saveAll(List<Student> students);

	void deleteById(Integer id);

	List<Student> findAllByCollege(Integer id);

	Student updateStudent(StudentUpdateRequest studentRequest);

	void detachStudentsFromCertificate(Integer certificateId);

	void deleteStudentsByCollegeId(Integer collegeId);

	List<Student> findAllStudents(Integer collegeId);

	List<Student> findAllStudentsWithCertificate(Integer collegeId);

}
