package calvert.dw.data.xml.parts;

import java.util.ArrayList;

public class XMLElement
{
	private String					tagName			= "";
	private ArrayList<XMLElement>	childElements	= new ArrayList<>();
	private ArrayList<XMLAttribute>	attributes		= new ArrayList<>();
	private String					content			= "";
	
	/**
	 * Creates XML element with tag name and content.
	 * 
	 * @param tagName
	 * @param attributes
	 */
	public XMLElement(String tagName, ArrayList<XMLAttribute> attributes)
	{
		this.tagName = tagName;
		this.attributes = attributes;
	}
	
	/**
	 * Creates XML element with tag name and content.
	 * 
	 * @param tagName
	 * @param content
	 */
	public XMLElement(String tagName, Object content)
	{
		this.tagName = tagName;
		this.content = String.valueOf(content);
	}
	
	/**
	 * Creates XML element with tag name.
	 * 
	 * @param tagName
	 */
	public XMLElement(String tagName)
	{
		this.tagName = tagName;
	}
	
	/**
	 * Nests an XML element within current element.
	 * 
	 * @param element
	 */
	public void add(XMLElement element)
	{
		childElements.add(element);
	}
	
	/**
	 * Searches for XML element with tag name destination and adds element to
	 * that destination.
	 * 
	 * @param element
	 * @param destination
	 */
	public void add(XMLElement element, String destination)
	{
		findElement(destination).getChildElements().add(element);
	}
	
	/**
	 * Searches all elements and child elements for a match based on the tag
	 * name.
	 * 
	 * @param tagName
	 * @return
	 */
	public XMLElement findElement(String tagName)
	{
		return findElement(tagName, this);
	}
	
	/**
	 * Recursive search algorithm for finding child elements.
	 * 
	 * @param tagName
	 * @param root
	 * @return XMLElement
	 */
	private XMLElement findElement(String tagName, XMLElement root)
	{
		// search all child elements
		for (XMLElement element : root.getChildElements())
			if (element.getTagName().equals(tagName))
				return element;
		
		// recursive search each child's elements
		for (XMLElement element : root.getChildElements())
		{
			element = findElement(tagName, element);
			if (element != null)
				return element;
		}
		
		return null;
	}
	
	/**
	 * Removes all child elements with tag name locally.
	 * 
	 * @param tagName
	 */
	public void remove(String tagName)
	{
		for (int i = 0; i < childElements.size(); i++)
			if (childElements.get(i).getTagName() == tagName)
				childElements.remove(i);
		
	}
	
	/**
	 * Searches all child elements for all the search terms, returns results in an array. If not found, null.
	 * 
	 * @param searchTerms
	 * @return
	 */
	public XMLElement[] search(String[] searchTerms)
	{
		XMLElement[] results = new XMLElement[searchTerms.length];
		
		for (int i = 0; i < searchTerms.length; i++)
			results[i] = null;
		
		for (XMLElement childElement : getChildElements())
		{
			for (int i = 0; i < searchTerms.length; i++)
			{
				if (childElement.getTagName().equals(searchTerms[i]))
				{
					results[i] = childElement;
				}
			}
		}
		
		return results;
	}
	
	/**
	 * Appends existing content.
	 * 
	 * @param content
	 */
	public void appendContent(String content)
	{
		this.content += content;
	}
	
	// GETTERS AND SETTERS
	
	public String getTagName()
	{
		return tagName;
	}
	
	public void setTagName(String tagName)
	{
		this.tagName = tagName;
	}
	
	public ArrayList<XMLElement> getChildElements()
	{
		return childElements;
	}
	
	public void setChildElements(ArrayList<XMLElement> childElements)
	{
		this.childElements = childElements;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
	
	public ArrayList<XMLAttribute> getAttributes()
	{
		return attributes;
	}
	
	public void setAttributes(ArrayList<XMLAttribute> attributes)
	{
		this.attributes = attributes;
	}
}
