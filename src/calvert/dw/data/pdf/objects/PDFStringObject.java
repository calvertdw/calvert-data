package calvert.dw.data.pdf.objects;

public class PDFStringObject extends PDFObject
{
	private static final String	PREFIX	= "(";
	private static final String	POSTFIX	= ")";
	
	private String				literal;
	
	public PDFStringObject(String literal)
	{
		this.literal = literal;
	}
	
	@Override
	public String toString()
	{
		return PREFIX + literal + POSTFIX;
	}
	
}
