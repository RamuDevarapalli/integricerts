package edu.integricert.model.dto;

import edu.integricert.model.Certificate;
import edu.integricert.model.Company;
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
public class EmployeeDto {
	private Integer id;
	private String name;
	private String gender;
	private Company company;
	private String joiningDate;
	private String designation;
	private String department;
	private String email;
	private String mobile;
	private String address;
	private String dob;
	private Certificate certificate;
}