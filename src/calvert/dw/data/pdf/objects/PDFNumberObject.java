package calvert.dw.data.pdf.objects;

public class PDFNumberObject extends PDFObject
{
	private double	doubleValue;
	private int		integerValue;
	private boolean	isDouble;
	
	public PDFNumberObject(double value)
	{
		isDouble = true;
		doubleValue = value;
	}
	
	public PDFNumberObject(int value)
	{
		isDouble = false;
		integerValue = value;
	}
	
	@Override
	public String toString()
	{
		if (isDouble)
			return String.valueOf(doubleValue);
		
		return String.valueOf(integerValue);
	}
}
