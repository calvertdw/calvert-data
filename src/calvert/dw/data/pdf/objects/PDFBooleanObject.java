package calvert.dw.data.pdf.objects;

public class PDFBooleanObject extends PDFObject
{
	private boolean	value;
	
	public PDFBooleanObject(boolean value)
	{
		this.value = value;
	}
	
	@Override
	public String toString()
	{
		return String.valueOf(value);
	}
	
}
