/**
 * 
 */
package com.hybris.showcase.callbacks;

/**
 * @author npavlovic
 * 
 *         CECS-111: Migrate my-account subscriptions page from telco
 */
public class RegistrationException extends Exception
{
	private String severity;

	public RegistrationException(final String severity, final String message, final Throwable cause)
	{
		super(message, cause);
		this.severity = severity;
	}

	public RegistrationException(final String severity, final String message)
	{
		super(message);
		this.severity = severity;
	}

	public String getSeverity()
	{
		return severity;
	}

	public void setSeverity(final String severity)
	{
		this.severity = severity;
	}
}
