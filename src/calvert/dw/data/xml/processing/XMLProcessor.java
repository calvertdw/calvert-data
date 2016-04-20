package calvert.dw.data.xml.processing;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Stack;

import calvert.dw.data.xml.parts.XMLAttribute;
import calvert.dw.data.xml.parts.XMLDeclaration;
import calvert.dw.data.xml.parts.XMLElement;

public class XMLProcessor extends XMLPreProcessor
{
	private ArrayList<XMLTag>	tags;
	
	public XMLProcessor(String pathname) throws FileNotFoundException
	{
		super(pathname);
		tags = process();
	}
	
	public XMLDeclaration loadDeclaration() throws Exception
	{
		if (tags.get(0).getType() == XMLTag.DECLARATION)
		{
			XMLDeclaration d = new XMLDeclaration(tags.get(0).getTagName());
			for (XMLAttribute att : tags.get(0).getAttributes())
			{
				if (att.getName().equals("version"))
					d.setVersion(att.getValue());
				if (att.getName().equals("encoding"))
					d.setEncoding(att.getValue());
				if (att.getName().equals("standalone"))
					d.setStandalone(att.getValue());
			}
			return d;
		}
		else
			return null;
	}
	
	public XMLElement loadRoot() throws Exception
	{
		Stack<XMLElement> stack = new Stack<>();
		XMLElement child;
		for (XMLTag tag : tags)
		{
			if (tag.getType() == XMLTag.OPEN)
			{
				XMLElement e = new XMLElement(tag.getTagName());
				e.setAttributes(tag.getAttributes());
				e.setContent(tag.getContent());
				stack.push(e);
			}
			else if (tag.getType() == XMLTag.CLOSE)
			{
				if (tag.getTagName().equals(stack.get(0).getTagName()))
					break;
				else
				{
					child = stack.pop();
					stack.peek().getChildElements().add(child);
					stack.peek().appendContent(tag.getContent());
				}
			}
		}
		return stack.pop();
	}
	
	public void displayTags()
	{
		for (XMLTag tag : tags)
		{
			tag.displayTag();
		}
	}
}
