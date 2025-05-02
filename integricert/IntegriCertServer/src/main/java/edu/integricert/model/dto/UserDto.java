package edu.integricert.model.dto;

import java.time.LocalDateTime;

import edu.integricert.model.constants.UserRole;
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
public class UserDto {

	private Integer id;

	private String name;

	private String email;

	private String userName;

	private String password;

	private String dateOfBirth;

	private String mobile;

	private String shippingAddress;

	private String billingAddress;

	private String medicalHistory;

	private String allergies;

	private UserRole role;

	private Short loginErrorCount;

	private LocalDateTime createdTime;

	private LocalDateTime updatedTime;

}