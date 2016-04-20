package calvert.dw.data.xml.processing;

import java.util.ArrayList;

import calvert.dw.data.xml.parts.XMLAttribute;

public class XMLTag
{
	public static final int			DECLARATION	= 0;
	public static final int			OPEN		= 1;
	public static final int			CLOSE		= 2;
	public static final int			COMMENT		= 3;
	
	private final int				type;
	private String					tagName;
	private String					content		= "";
	private ArrayList<XMLAttribute>	attributes	= new ArrayList<>();
	
	public XMLTag(int type)
	{
		this.type = type;
	}
	
	public void displayTag()
	{
		System.out.print(getType() + " " + getTagName() + " ");
		for (XMLAttribute att : getAttributes())
			System.out.print(att.getName() + ":" + att.getValue() + " ");
		System.out.println(getContent());
	}
	
	public int getType()
	{
		return type;
	}
	
	public String getTagName()
	{
		return tagName;
	}
	
	public void setTagName(String tagName)
	{
		this.tagName = tagName;
	}
	
	public void addAttribute(XMLAttribute e)
	{
		attributes.add(e);
	}
	
	public ArrayList<XMLAttribute> getAttributes()
	{
		return attributes;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
}
