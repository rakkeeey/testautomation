package com.seleniumeasy.framework.reporting;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;

/**
 * Custom Exception for the framework
 * 
 * @author Rakesh
 *
 */
public class FrameworkException extends Exception {
		
	private static final long serialVersionUID = 1L;
	private static Log logger = new Log4JLogger("FrameworkException");

	public FrameworkException() {
		super();
	}

	public FrameworkException(String message) {
		super(message);
	}

	public FrameworkException(Exception exception) {
		super(exception);
	}
	
	public FrameworkException(Throwable cause) {
		super(cause);
	}

	public FrameworkException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public FrameworkException(Exception exception,String methodName){
		logger.error("====================================================================================================================");
		logger.error("ExceptionLocation :"+methodName);
		logger.error("Message :"+exception.getMessage());
		logger.error("Cause :"+exception.getCause());
		logger.error("ExceptionType: " +exception.toString());
		logger.error("====================================================================================================================");
	}
}
