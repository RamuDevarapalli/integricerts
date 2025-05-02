package edu.integricert.request;

import edu.integricert.model.Certificate;
import edu.integricert.model.College;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentUpdateRequest {
	private int id;
	private String userEmail;
	private String name;
	private String gender;
	private College college;
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
