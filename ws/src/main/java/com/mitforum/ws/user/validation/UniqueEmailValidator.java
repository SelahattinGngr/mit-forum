package com.mitforum.ws.user.validation;

import com.mitforum.ws.user.User;
import com.mitforum.ws.user.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String > {
	@Autowired
	UserRepository userRepository;

	@Override
	public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
		User inDB = userRepository.findByEmail(s);
		return inDB == null;
	}
}
