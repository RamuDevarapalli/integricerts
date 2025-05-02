package edu.integricert.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import edu.integricert.model.constants.CertificateType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "certificate")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Certificate implements Serializable {

	private static final long serialVersionUID = 7943105079795379972L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, unique = true)
	private String value;

	@Lob
	@Column(name = "data", columnDefinition = "LONGBLOB")
	private byte[] data;

	@Enumerated(EnumType.STRING)
	@Column(name = "certificate_type", nullable = false)
	private CertificateType certificateType;

	@OneToOne(mappedBy = "certificate", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "certificate" })
	private Student student;
	
	@OneToOne(mappedBy = "certificate", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "certificate" })
	private Employee employee;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false, nullable = false)
	private LocalDateTime createdAt;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@CreationTimestamp
	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@Column(name = "modified_by")
	private String modifiedBy;
}
