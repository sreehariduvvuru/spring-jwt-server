package dev.danvega.jwt.controller;

import java.security.Principal;

import dev.danvega.jwt.UserDTO;
import dev.danvega.jwt.model.User;
import dev.danvega.jwt.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/")
	public String home(Principal principal) {
		return "Hello, " + principal.getName();
	}

	@PreAuthorize("hasAuthority('SCOPE_read')")
	@GetMapping("/secure")
	public String secure() {
		return "This is secured!";
	}

		@PostMapping("/registration")
	public ResponseEntity registration(@RequestBody UserDTO userData)
	{

		userData.setPassword(passwordEncoder.encode(userData.getPassword()));
		User user=
				customUserDetailsService.insertUserDetail(userData);

		return ResponseEntity.ok("Registration successfull");
	}
}
