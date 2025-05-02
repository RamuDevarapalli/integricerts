package edu.integricert.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "employee")
public class Employee implements Serializable {

	private static final long serialVersionUID = -2183920524258167096L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(nullable = false)
	private String gender;

	@Column(nullable = false)
	private String joiningDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", nullable = false)
	@JsonIgnoreProperties({ "employees", "hibernateLazyInitializer", "handler" })
	private Company company;

	@Column(nullable = false)
	private String designation;

	@Column(nullable = false)
	private String department;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String mobile;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String dob;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "certificate_id")
	@JsonIgnoreProperties({ "employee", "hibernateLazyInitializer", "handler" })
	private Certificate certificate;

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
