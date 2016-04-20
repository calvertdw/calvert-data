package calvert.dw.data.xml;

import java.io.FileNotFoundException;

import calvert.dw.data.parsing.FileParser;
import calvert.dw.data.xml.parts.XMLDeclaration;
import calvert.dw.data.xml.parts.XMLElement;
import calvert.dw.data.xml.processing.XMLProcessor;
import calvert.dw.data.xml.processing.XMLWriter;

/**
 * XML Library:
 * A library for reading a writing to XML files. Follows convention, however, not fully-featured. i.e. Encoding not yet
 * supported.
 * 
 * @author Duncan
 * 
 */
public class XMLDocument
{
	private XMLDeclaration	declaration	= new XMLDeclaration("xml");
	private XMLElement		root		= new XMLElement("");
	private String			pathname;
	
	public XMLDocument(String pathname)
	{
		this.pathname = pathname;
	}
	
	public void load()
	{
		XMLProcessor processor;
		try
		{
			processor = new XMLProcessor(pathname);
			try
			{
				declaration = processor.loadDeclaration();
			}
			catch (Exception e)
			{
				System.err.println("XMLDocument: \"" + pathname + "\" Could not load declaration.");
			}
			try
			{
				root = processor.loadRoot();
			}
			catch (Exception e)
			{
				System.err.println("XMLDocument: \"" + pathname + "\" Could not load root.");
			}
			processor.closeFile();
		}
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
	}
	
	public void save(String pathname)
	{
		FileParser parser;
		try
		{
			parser = new FileParser(pathname);
			parser.clear();
			parser.writeString(XMLWriter.writeDeclaration(declaration));
			parser.writeString(XMLWriter.writeElement(root, 0));
			parser.closeFile();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public void setPathname(String pathname)
	{
		this.pathname = pathname;
	}
	
	public String getPathname()
	{
		return pathname;
	}
	
	public void save()
	{
		save(pathname);
	}
	
	public XMLDeclaration getDeclaration()
	{
		return declaration;
	}
	
	public void setDeclaration(XMLDeclaration declaration)
	{
		this.declaration = declaration;
	}
	
	public XMLElement getRoot()
	{
		return root;
	}
	
	public String getValue(String tagName)
	{
		return getRoot().findElement(tagName).getContent();
	}
	
	public void setValue(String tagName, String value)
	{
		getRoot().findElement(tagName).setContent(value);
	}
	
	public void addElement(String tagName, String value)
	{
		getRoot().add(new XMLElement(tagName, value));
	}
	
	public void addElement(String parentTag, String tagName, String value)
	{
		getRoot().add(new XMLElement(tagName, value), parentTag);
	}
	
	public void removeElement(String tagName)
	{
		getRoot().remove(tagName);
	}
	
	public void setRoot(XMLElement root)
	{
		this.root = root;
	}
}
