package edu.integricert.service;

import edu.integricert.request.SignUpRequest;
import edu.integricert.request.SigninRequest;
import edu.integricert.response.JwtAuthenticationResponse;

public interface AuthenticationService {
	JwtAuthenticationResponse signup(SignUpRequest request);

	JwtAuthenticationResponse signin(SigninRequest request);
}
