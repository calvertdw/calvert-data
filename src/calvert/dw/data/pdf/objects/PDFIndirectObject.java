package calvert.dw.data.pdf.objects;

public class PDFIndirectObject extends PDFObject
{
	private static final String	PREFIX				= "obj";
	private static final String	POSTFIX				= "endobj";
	
	private int					objectNumber;
	private int					generationNumber	= 0;
	private PDFObject			content				= new PDFNullObject();
	
	public PDFIndirectObject(int number)
	{
		objectNumber = number;
	}
	
	public PDFIndirectObject(int number, PDFObject content)
	{
		objectNumber = number;
		this.content = content;
	}
	
	public void setContent(PDFObject content)
	{
		this.content = content;
	}
	
	@Override
	public String toString()
	{
		String representation = String.valueOf(objectNumber);
		representation += ' ' + String.valueOf(generationNumber);
		representation += ' ' + PREFIX + '\n';
		
		representation += content.toString() + '\n';
		
		representation += POSTFIX;
		
		return representation;
	}
}
