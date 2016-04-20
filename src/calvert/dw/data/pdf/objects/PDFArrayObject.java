package calvert.dw.data.pdf.objects;

import java.util.ArrayList;

public class PDFArrayObject extends ArrayList<PDFObject>
{
	private static final long	serialVersionUID	= -174693807242833434L;
	private static final String	PREFIX				= "[";
	private static final String	DELIMITER			= " ";
	private static final String	POSTFIX				= "]";
	
	public PDFArrayObject(PDFObject... objects)
	{
		for (PDFObject object : objects)
			add(object);
	}
	
	public String toString()
	{
		String s = PREFIX;
		
		for (int i = 0; i < size(); i++)
		{
			s += get(i).toString();
			if (i != size())
				s += DELIMITER;
		}
		
		return s += POSTFIX;
	}
}
