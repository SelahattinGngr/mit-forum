package com.mitforum.ws.user.exception;

import com.mitforum.ws.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Collections;
import java.util.Map;

public class NotUniqueEmailException extends RuntimeException {

	public NotUniqueEmailException() {
		super(Messages.getMessageForLocale("mitforum.error.validation", LocaleContextHolder.getLocale()));
	}

	public Map<String, String> getValidationErrors() {
		return Collections.singletonMap("email", Messages.getMessageForLocale("mitforum.constraint.email.notunique", LocaleContextHolder.getLocale()));
	}
}
