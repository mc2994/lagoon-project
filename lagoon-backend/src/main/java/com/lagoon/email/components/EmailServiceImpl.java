package com.lagoon.email.components;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

//	@Autowired
//	JavaMailSender mailSender;

	@Autowired
	Configuration freeMarkerConfig;

	public final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(5);

	public void sendEmail(Mail mail) {
		MimeMessage mimeMessage = null; //mailSender.createMimeMessage();

		try {

			final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			mimeMessageHelper.setSubject(mail.getMailSubject());
			mimeMessageHelper.setFrom(mail.getMailFrom());
			if (mail.getMailTos() != null && mail.getMailTos().length > 0) {
				mimeMessageHelper.setTo(mail.getMailTos());
			} else {
				mimeMessageHelper.setTo(mail.getMailTo());
			}
			mail.setMailContent(geContentFromTemplate(mail.getEmailTemp(), mail.getModel()));
			mimeMessageHelper.setText(mail.getMailContent(), true);

//mailSender.send(mimeMessageHelper.getMimeMessage());

			quickService.submit(new Runnable() {
				@Override
				public void run() {
					try {
						//mailSender.send(mimeMessageHelper.getMimeMessage());
						logger.info("sending email nowwwww...");
					} catch (Exception e) {
						logger.error("Exception occur while send a mail : ", e);
					}
				}
			});

		} catch (MessagingException e) {
			logger.error("Error sending email MessagingException: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Error sending email Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public String geContentFromTemplate(Mail.EmailTemplate template, Map<String, Object> model) {
		StringBuffer content = new StringBuffer();

		try {
			freeMarkerConfig.setClassForTemplateLoading(this.getClass(), "/emailtemplate/");
			if (template != null) {
				content.append(FreeMarkerTemplateUtils
						.processTemplateIntoString(freeMarkerConfig.getTemplate(template.getTemplate()), model));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.toString();
	}

}
