package br.com.expirationNotificationRobot.exceptions;

public class WhenNotifyException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public WhenNotifyException(String message, Throwable cause) {
		super(message, cause);
	}

	public WhenNotifyException(String message) {
		super(message);
	}

}
