package edu.integricert.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.integricert.model.User;
import edu.integricert.request.PasswordRequest;
import edu.integricert.request.UserRequest;
import edu.integricert.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/all")
	public ResponseEntity<List<User>> users() {
		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping("/all/{role}")
	public ResponseEntity<List<User>> users(@PathVariable String role) {
		return ResponseEntity.ok(userService.findAllByRole(role));
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> usersById(@PathVariable Integer id) {
		return ResponseEntity.ok(userService.findById(id));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map> deleteUser(@PathVariable Integer id, Principal principal) throws Exception {
		this.userService.deleteById(id);
		Map<String, Object> response = new HashMap<>();
		response.put("message", "User Deleted Successfully");
		return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
	}

	@PostMapping("/update")
	public ResponseEntity<User> updateUser(@RequestBody UserRequest request, Principal principal) throws Exception {
		Integer id = request.getId();
		User user = userService.findById(id);
		user.setName(request.getName());
		user.setRole(request.getRole());
		user.setUserName(request.getUsername());
		user.setAddress(request.getAddress());
		user.setDateOfBirth(request.getDateOfBirth());
		user.setMobile(request.getMobile());
		user.setUpdatedTime(LocalDateTime.now());
		userService.save(user);
		return new ResponseEntity<>(user, HttpStatusCode.valueOf(200));
	}

	@PostMapping("/updatePassword")
	public ResponseEntity<User> updatePassword(@RequestBody PasswordRequest request, Principal principal)
			throws Exception {
		Integer id = request.getId();
		User user = userService.findById(id);
		String existingPassword = user.getPassword();
		String oldPassword = request.getOldPassword();
		if (existingPassword.equals(oldPassword)) {

		}
		user.setPassword(request.getNewPassword());
		user.setUpdatedTime(LocalDateTime.now());
		userService.save(user);
		return new ResponseEntity<>(user, HttpStatusCode.valueOf(200));
	}

}
