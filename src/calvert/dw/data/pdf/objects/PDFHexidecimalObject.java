package calvert.dw.data.pdf.objects;

import javax.xml.bind.DatatypeConverter;

public class PDFHexidecimalObject extends PDFObject
{
	private static final String	PREFIX	= "<";
	private static final String	POSTFIX	= ">";
	
	private String				hexString;
	
	public PDFHexidecimalObject(String hexString)
	{
		this.hexString = hexString;
	}
	
	public static String toHexString(byte[] array)
	{
		return DatatypeConverter.printHexBinary(array);
	}
	
	public static byte[] toByteArray(String s)
	{
		return DatatypeConverter.parseHexBinary(s);
	}
	
	@Override
	public String toString()
	{
		return PREFIX + hexString + POSTFIX;
	}
	
}
