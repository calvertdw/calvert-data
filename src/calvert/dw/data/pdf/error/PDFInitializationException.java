package calvert.dw.data.pdf.error;

public class PDFInitializationException extends Exception
{
	private static final long	serialVersionUID	= -6952432662205858811L;
	
	public PDFInitializationException(String message)
	{
		System.err.println(message);
	}
}
