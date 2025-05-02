package edu.integricert.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import edu.integricert.model.User;

public interface UserService {
	UserDetailsService userDetailsService();

	void save(User user);

	void saveAll(List<User> users);

	void updateRole(Integer userId, String role);

	List<User> findAll();

	User findById(Integer id);

	User findByEmail(String email);

	void deleteById(Integer id);

	List<User> findAllByRole(String role);

}
