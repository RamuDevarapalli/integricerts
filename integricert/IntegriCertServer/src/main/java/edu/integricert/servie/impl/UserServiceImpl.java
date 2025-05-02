package edu.integricert.servie.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import edu.integricert.model.User;
import edu.integricert.repository.UserRepository;
import edu.integricert.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) {
				return userRepository.findByEmail(username);
			}
		};
	}

	@Override
	public void updateRole(Integer userId, String role) {
		userRepository.updateRole(userId, role);
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public void saveAll(List<User> users) {
		userRepository.saveAll(users);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(Integer id) {
		Optional<User> optional = userRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	public List<User> findAllByRole(String role) {
		return userRepository.findAllByRole(role);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
