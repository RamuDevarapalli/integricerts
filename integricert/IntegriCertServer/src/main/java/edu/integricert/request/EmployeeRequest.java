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
public class EmployeeRequest {
	private String userEmail;
	private String name;
	private String gender;
	private String companyName;
	private String joiningDate;
	private String designation;
	private String department;
	private String email;
	private String mobile;
	private String address;
	private String dob;
	private Certificate certificate;
}
