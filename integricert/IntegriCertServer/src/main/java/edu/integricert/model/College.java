package edu.integricert.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "college")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class College implements Serializable {

	private static final long serialVersionUID = 4793712039773980508L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Column(nullable = false)
	private String location;

	@Column(name = "registration_id", nullable = false, unique = true)
	private String registrationId;

	@Column(name = "established_year", nullable = false)
	private int establishedYear;

	@Column(nullable = false, unique = true)
	private String mobile;

	@Column(nullable = false, unique = true)
	private String email;

	@OneToMany(mappedBy = "college", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "college" })
	private List<Student> students;

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
