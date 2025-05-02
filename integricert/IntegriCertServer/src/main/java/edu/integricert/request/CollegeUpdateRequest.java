package edu.integricert.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CollegeUpdateRequest {
	private int id;
	private String userEmail;
	private String name;
	private String location;
	private String registrationId;
	private int establishedYear;
	private String mobile;
	private String email;
}
