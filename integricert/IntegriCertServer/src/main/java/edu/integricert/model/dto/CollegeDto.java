package edu.integricert.model.dto;

import java.util.List;

import edu.integricert.model.Student;
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
public class CollegeDto {

	private Integer id;

	private String name;

	private String location;

	private String registrationId;

	private int establishedYear;

	private String mobile;

	private String email;

	private List<Student> students;

}