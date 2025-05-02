package edu.integricert.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import edu.integricert.model.constants.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "user")
public class User implements UserDetails, Serializable {

	@Serial
	private static final long serialVersionUID = 1905122041950251207L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(unique = true)
	private String email;

	@Column(name = "user_name")
	private String userName;

	@Column(nullable = false)
	private String password;

	@Column(name = "date_of_birth")
	private String dateOfBirth;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "address")
	private String address;

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private UserRole role;

	@Column(name = "login_error_count")
	private Short loginErrorCount;

	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdTime;

	@Column(name = "updated_at")
	private LocalDateTime updatedTime;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}