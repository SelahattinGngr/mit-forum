package com.mitforum.ws.user.exception;

import org.springframework.context.i18n.LocaleContextHolder;

import com.mitforum.ws.shared.Messages;

public class ActivationNotificationException extends RuntimeException{
	public ActivationNotificationException() {
		super(Messages.getMessageForLocale("mitforum.create.user.email.failure", LocaleContextHolder.getLocale()));
	}
}
