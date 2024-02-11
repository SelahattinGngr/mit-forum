package com.mitforum.ws.user.dto;

import com.mitforum.ws.user.User;
import com.mitforum.ws.user.validation.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreate(
		@NotBlank(message = "{mitforum.constraint.username.notblank}")
		@Size(min = 4, max = 255)
		String  username,

		@Email
		@NotBlank
		@UniqueEmail
		String  email,

		@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{mitforum.constraint.password.pattern}")
		@Size(min = 8, max = 255)
		String  password
) {

	public User toUser() {
		User user = new User();
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(password);
		return user;
	}
}
