package com.mitforum.ws.user;

import com.mitforum.ws.email.EmailService;
import com.mitforum.ws.user.exception.ActivationNotificationException;
import com.mitforum.ws.user.exception.InvalidTokenException;
import com.mitforum.ws.user.exception.NotUniqueEmailException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	EmailService emailService;

	@Transactional(rollbackOn = MailException.class)
	public void save(User user) {
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setActivationToken(UUID.randomUUID().toString());
			userRepository.saveAndFlush(user);
			emailService.sendActivationEmail(user.getEmail(), user.getActivationToken());
		} catch (DataIntegrityViolationException ex) {
			throw new NotUniqueEmailException();
		} catch (MailException ex) {
			throw new ActivationNotificationException();
		}
	}

	public void activateUser(String token) {
		User inDB = userRepository.findByActivationToken(token);
		if (inDB == null) {
			throw new InvalidTokenException();
		}
		inDB.setActive(true);
		inDB.setActivationToken(null);
		userRepository.save(inDB);
	}
}