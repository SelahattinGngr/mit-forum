package com.mitforum.ws.email;

import com.mitforum.ws.configuration.MitForumProperties;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

	JavaMailSenderImpl mailSender;

	@Autowired
	MessageSource messageSource;

	@Autowired
	MitForumProperties mitForumProperties;

	@PostConstruct
	public void initialize() {
		this.mailSender = new JavaMailSenderImpl();
		mailSender.setHost(mitForumProperties.getEmail().host());
		mailSender.setPort(mitForumProperties.getEmail().port());
		mailSender.setUsername(mitForumProperties.getEmail().username());
		mailSender.setPassword(mitForumProperties.getEmail().password());

		Properties properties = mailSender.getJavaMailProperties();
		properties.put("mail.smtp.starttls.enable", "true");
	}

	String activationEmail = """
			<html>
				<body>
					<h1>${title}</h1>
					<a href="${url}">${clickhere}</a>
				</body>
			</html>
			""";

	public void sendActivationEmail(String email, String activationToken) {
		var activationUrl = mitForumProperties.getClient().host() + "/activation/" + activationToken;
		var title = messageSource.getMessage("mitforum.mail.user.created.title", null, LocaleContextHolder.getLocale());
		var clickhere = messageSource.getMessage("mitforum.mail.click.here", null, LocaleContextHolder.getLocale());
		var mailBody = activationEmail
				.replace("${url}", activationUrl)
				.replace("${title}", title)
				.replace("${clickhere}", clickhere);

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
		//SimpleMailMessage message = new SimpleMailMessage();
		try {
			message.setFrom(mitForumProperties.getEmail().from());
			message.setTo(email);
			message.setSubject(title);
			message.setText(mailBody, true);
		}catch (MessagingException e) {
			e.printStackTrace();
		}
		this.mailSender.send(mimeMessage);
	}
}
