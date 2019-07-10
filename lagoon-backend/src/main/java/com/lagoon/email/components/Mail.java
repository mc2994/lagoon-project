package com.lagoon.email.components;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Mail {

	private String mailFrom;

	private String mailTo;

	private String[] mailTos;

	private String mailCc;

	private String mailBcc;

	private String mailSubject;

	private String mailContent;

	private String contentType;

	private List<Object> attachments;

	private Map<String, Object> model;

	private Mail.EmailTemplate emailTemp;

	public enum EmailTemplate {

		POTF("potf-template.txt"), CASE_SETUP_ABORTED("case-creation-error-notification-template.txt"),
		CASE_SETUP_INIT("case-creation-init-notification-template.txt"),
		CASE_SETUP_COMPLETED("case-creation-success-notification-template.txt"),
		WORKFLOW_EMAIL("workflow-email-template.txt"), WORKFLOW_EMAIL_INITIATOR("workflow-initiator.txt"),
		WORKFLOW_EMAIL_PARTICIPANT("workflow-participants.txt");

		private String template;

		EmailTemplate(String temp) {
			this.template = temp;
		}

		public String getTemplate() {
			return template;
		}

		public void setTemplate(String template) {
			this.template = template;
		}
	}

	public Mail() {
		contentType = "text/plain";
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getMailBcc() {
		return mailBcc;
	}

	public void setMailBcc(String mailBcc) {
		this.mailBcc = mailBcc;
	}

	public String getMailCc() {
		return mailCc;
	}

	public void setMailCc(String mailCc) {
		this.mailCc = mailCc;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public Date getMailSendDate() {
		return new Date();
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public List<Object> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Object> attachments) {
		this.attachments = attachments;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

	public EmailTemplate getEmailTemp() {
		return emailTemp;
	}

	public void setEmailTemp(EmailTemplate emailTemp) {
		this.emailTemp = emailTemp;
	}

	public String[] getMailTos() {
		return mailTos;
	}

	public void setMailTos(String[] mailTos) {
		this.mailTos = mailTos;
	}
}
