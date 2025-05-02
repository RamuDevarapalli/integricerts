package edu.integricert.model.dto;

import java.time.LocalDateTime;

import edu.integricert.model.constants.CertificateType;
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
public class CertificateDto {

	private Integer id;

	private String certificate;

	private byte[] data;

	private CertificateType CertificateType;

	private LocalDateTime createdAt;

	private String createdBy;

}