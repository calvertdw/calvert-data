package calvert.dw.data.pdf.objects;

public class PDFStreamObject extends PDFObject
{
	private static final String	PREFIX	= "stream";
	private static final String	POSTFIX	= "endstream";
	
	private PDFDictionaryObject	dictionary;
	private String				stream	= "";
	private int					length;
	
	public PDFStreamObject()
	{
	}
	
	public void appendStream(String string)
	{
		stream += string;
		length += string.length();
	}
	
	public void clearStream()
	{
		stream = "";
		length = 0;
	}
	
	@Override
	public String toString()
	{
		this.dictionary = new PDFDictionaryObject();
		
		dictionary.addEntry(new PDFNameObject("Length"), new PDFNumberObject(length));
		
		String object = dictionary.toString();
		
		object += '\n';
		object += PREFIX + '\n';
		object += stream + '\n';
		object += POSTFIX;
		
		return object;
	}
}
