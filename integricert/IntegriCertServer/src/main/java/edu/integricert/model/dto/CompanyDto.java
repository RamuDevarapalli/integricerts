package edu.integricert.model.dto;

import java.util.List;

import edu.integricert.model.Employee;
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
public class CompanyDto {

	private Integer id;

	private String name;

	private String location;

	private String registrationId;

	private int establishedYear;

	private String mobile;

	private String email;

	private List<Employee> emplooyees;

}