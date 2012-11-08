package com.asu.edu.base.vo;

import java.util.Iterator;
import java.util.Set;
import java.util.Random;
import java.util.UUID;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import java.security.SecureRandom;
import java.math.BigInteger;

public class UserRegistrationServiceVO {

	private MailSender mailSender;
	private Set<String> userEmailIds;
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getUserEmailIds() {
		return userEmailIds;
	}

	public void setUserEmailIds(Set<String> userEmailIds) {
		this.userEmailIds = userEmailIds;
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void uponSuccessfulRegistration() {

		SimpleMailMessage[] mailMessageArray = new SimpleMailMessage[userEmailIds.size()];
		Iterator<String> iterator = userEmailIds.iterator();
		for (int index = 0; iterator.hasNext(); index++) {

			int length = 14;
			char[] text = new char[length];
			Random rand = new Random();
			Random rng = new Random();

			for (int i = 0; i < length; i++) {
				String characters = "!567_@#$%&*89a0bd12efghirstuopqcxyvwj34klmnz";

				text[i] = characters.charAt(rng.nextInt(characters.length()));
			}

			String stringPwd = text.toString();
			SimpleMailMessage message = new SimpleMailMessage();
			/*
			 * Random rand = new Random(); int rng =
			 * rand.nextInt(10000000-1000000) + 1000000;
			 */
			setPassword(stringPwd);
			String toAddress = iterator.next();
			message.setTo(toAddress);
			message.setSubject("Password Reset");
			// message.setText("The user '" + toAddress +
			// "' is successfully registered");
			message.setText("Your new password has been reset .\n\nYour new password is " + stringPwd+ "\n");
			mailMessageArray[index] = message;
		}

		System.out.println("Email Sent !!");
		mailSender.send(mailMessageArray);
	}

}
