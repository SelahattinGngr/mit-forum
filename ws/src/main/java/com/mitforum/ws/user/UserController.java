package com.mitforum.ws.user;

import com.mitforum.ws.error.ApiError;
import com.mitforum.ws.shared.GenericMessage;
import com.mitforum.ws.shared.Messages;
import com.mitforum.ws.user.dto.UserCreate;
import com.mitforum.ws.user.exception.ActivationNotificationException;
import com.mitforum.ws.user.exception.InvalidTokenException;
import com.mitforum.ws.user.exception.NotUniqueEmailException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/api/v1/users")
	GenericMessage createUser(@Valid @RequestBody UserCreate user) {
		userService.save(user.toUser());
		String message = Messages.getMessageForLocale("mitforum.constraint.user.success.message", LocaleContextHolder.getLocale());
		return new GenericMessage(message);
	}

	@PatchMapping("/api/v1/users/{token}/active")
	GenericMessage activateUser(@PathVariable String token) {
		userService.activateUser(token);
		String message = Messages.getMessageForLocale("mitforum.activate.user.success.message", LocaleContextHolder.getLocale());
		return new GenericMessage(message);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<ApiError> handleMethodArgNotValidEx(MethodArgumentNotValidException exception) {
		ApiError apiError = new ApiError();
		apiError.setPath("/api/v1/users");
		String message = Messages.getMessageForLocale("mitforum.error.validation", LocaleContextHolder.getLocale());
		apiError.setMessage(message);
		apiError.setStatus(400);
		var validationErrors = exception.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (existing, replacing) -> existing));
		apiError.setValidationErrors(validationErrors);
		return ResponseEntity.status(400).body(apiError);
	}

	@ExceptionHandler(NotUniqueEmailException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<ApiError> handleNotUniqueException(NotUniqueEmailException exception) {
		ApiError apiError = new ApiError();
		apiError.setPath("/api/v1/users");
		apiError.setMessage(exception.getMessage());
		apiError.setStatus(400);
		apiError.setValidationErrors(exception.getValidationErrors());
		return ResponseEntity.status(400).body(apiError);
	}

	@ExceptionHandler(ActivationNotificationException.class)
	ResponseEntity<ApiError> activationNotificationException(ActivationNotificationException exception) {
		ApiError apiError = new ApiError();
		apiError.setPath("/api/v1/users");
		apiError.setMessage(exception.getMessage());
		apiError.setStatus(502);
		return ResponseEntity.status(502).body(apiError);
	}

	@ExceptionHandler(InvalidTokenException.class)
	ResponseEntity<ApiError> handleInvalidTokenException(InvalidTokenException exception) {
		ApiError apiError = new ApiError();
		apiError.setPath("/api/v1/users");
		apiError.setMessage(exception.getMessage());
		apiError.setStatus(400);
		return ResponseEntity.status(400).body(apiError);
	}
}
