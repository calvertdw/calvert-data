package calvert.dw.data.pdf.objects;

import java.util.ArrayList;

public class PDFDictionaryObject extends PDFObject
{
	private static final String			PREFIX		= "<<  ";
	private static final String			DELIMETER	= "\n";
	private static final String			POSTFIX		= ">>";
	
	private int							indentation	= 0;
	private ArrayList<PDFKeyValuePair>	entries		= new ArrayList<>();
	
	public void addEntry(PDFNameObject key, PDFObject value)
	{
		entries.add(new PDFKeyValuePair(key, value));
	}
	
	public PDFObject getValue(PDFNameObject key)
	{
		for (PDFKeyValuePair entry : entries)
			if (entry.getKey().getName().equals(key.getName()))
				return entry.getValue();
		
		return null;
	}
	
	/**
	 * Recursive toString method for indentation.
	 * 
	 * @param indent
	 * @return dictionary
	 */
	private String toString(int indent)
	{
		String s = getIndent(indent) + PREFIX;
		s += entries.get(0).toString();
		
		for (int i = 1; i < entries.size(); i++)
		{
			s += DELIMETER;
			s += getIndent(indent + 1);
			s += entries.get(i).toString();
		}
		
		s += DELIMETER;
		s += getIndent(indent);
		s += POSTFIX;
		
		return s;
	}
	
	private String getIndent(int indent)
	{
		String s = "";
		for (int i = 0; i < indent; i++)
			s += "\t";
		
		return s;
	}
	
	public void setIndentation(int indentation)
	{
		this.indentation = indentation;
	}
	
	public int getIndentation()
	{
		return indentation;
	}
	
	@Override
	public String toString()
	{
		if (entries.size() == 0)
			return "null";
		if (entries.size() == 1)
			return PREFIX + entries.get(0).toString() + " " + POSTFIX;
		
		return toString(indentation);
	}
}
