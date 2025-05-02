package edu.integricert.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordRequest {
	private Integer id;
	private String oldPassword;
	private String newPassword;
}
