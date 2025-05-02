package edu.integricert.servie.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.integricert.model.User;
import edu.integricert.model.constants.UserRole;
import edu.integricert.repository.UserRepository;
import edu.integricert.request.SignUpRequest;
import edu.integricert.request.SigninRequest;
import edu.integricert.response.JwtAuthenticationResponse;
import edu.integricert.service.AuthenticationService;
import edu.integricert.service.JwtService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	@Override
	public JwtAuthenticationResponse signup(SignUpRequest request) {
		var user = User.builder().name(request.getName()).userName(request.getUserName()).email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword())).mobile(request.getMobile())
				.address(request.getAddress()).dateOfBirth(request.getDateOfBirth()).role(UserRole.USER).build();
		userRepository.save(user);
		var jwt = jwtService.generateToken(user);
		return JwtAuthenticationResponse.builder().user(user).token(jwt).build();
	}

	@Override
	public JwtAuthenticationResponse signin(SigninRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var user = userRepository.findByEmail(request.getEmail());
		var jwt = jwtService.generateToken(user);
		return JwtAuthenticationResponse.builder().user(user).token(jwt).build();
	}
}
