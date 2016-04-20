package calvert.dw.data.xml.parts;

public class XMLAttribute
{
	String	name	= "";
	String	value	= "";
	
	public XMLAttribute(String name, String value)
	{
		this.name = name;
		this.value = value;
	}
	
	public XMLAttribute(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
}
