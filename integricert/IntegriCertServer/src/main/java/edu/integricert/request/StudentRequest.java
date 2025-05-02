package edu.integricert.request;

import edu.integricert.model.Certificate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentRequest {
	private String userEmail;
	private String name;
	private String gender;
	private String collegeName;
	private String course;
	private String email;
	private String mobile;
	private String address;
	private String academicYear;
	private String courseResult;
	private String degree;
	private String dob;
	private Certificate certificate;
}
