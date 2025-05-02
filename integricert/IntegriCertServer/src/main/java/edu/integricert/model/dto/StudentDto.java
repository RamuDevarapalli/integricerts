package edu.integricert.model.dto;

import edu.integricert.model.Certificate;
import edu.integricert.model.College;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StudentDto {

	private Integer id;

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