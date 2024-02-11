package com.mitforum.ws.user.exception;

import com.mitforum.ws.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

public class InvalidTokenException extends RuntimeException {
	public InvalidTokenException() {
		super(Messages.getMessageForLocale("mitforum.activate.user.invalid.token", LocaleContextHolder.getLocale()));
	}
}
