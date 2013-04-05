package app.core.exception;

public class UnknownTypeException extends Exception
{
	private String message;
	
	public UnknownTypeException(String message)
	{
		this.message = message;
	}
	
	public String getMessage()
	{
		return this.message;
	}

}
