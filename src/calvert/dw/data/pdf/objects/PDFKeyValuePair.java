package calvert.dw.data.pdf.objects;

public class PDFKeyValuePair
{
	private static final String	DELIMITER	= " ";
	private PDFNameObject		key;
	private PDFObject			value;
	
	public PDFKeyValuePair(PDFNameObject key, PDFObject value)
	{
		this.key = key;
		this.value = value;
	}
	
	public PDFNameObject getKey()
	{
		return key;
	}
	
	public PDFObject getValue()
	{
		return value;
	}
	
	public String toString()
	{
		String s = key.toString();
		s += DELIMITER;
		if (value instanceof PDFDictionaryObject)
			((PDFDictionaryObject) value).setIndentation(((PDFDictionaryObject) value).getIndentation() + 1);
		s += value.toString();
		
		return s;
	}
}
