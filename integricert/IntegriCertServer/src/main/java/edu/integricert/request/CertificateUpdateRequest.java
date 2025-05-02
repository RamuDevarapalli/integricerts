package edu.integricert.request;

import edu.integricert.model.constants.CertificateType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CertificateUpdateRequest {
	private int id;
	private String userEmail;
	private String value;
	private byte[] data;
	private CertificateType certificateType;
}
