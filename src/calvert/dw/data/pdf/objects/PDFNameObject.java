package calvert.dw.data.pdf.objects;

public class PDFNameObject extends PDFObject
{
	private static final String	PREFIX	= "/";
	private String				name;
	
	public PDFNameObject(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	@Override
	public String toString()
	{
		return PREFIX + name;
	}
}
