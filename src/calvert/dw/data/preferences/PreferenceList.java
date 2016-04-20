package calvert.dw.data.preferences;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import calvert.dw.data.parsing.FileParser;
import calvert.dw.data.xml.XMLCompatible;
import calvert.dw.data.xml.parts.XMLElement;

public class PreferenceList extends ArrayList<Preference> implements XMLCompatible
{
	private static final long	serialVersionUID	= 1794538943001842360L;
	private String				pathname			= "default.pref";
	
	public PreferenceList(String pathname)
	{
		setPathname(pathname);
	}
	
	public void load()
	{
		FileParser parser;
		try
		{
			parser = new FileParser(pathname);
			while (parser.remainingBytes() > 0 && parser.peekChar() != '\n')
			{
				Preference preference = new Preference("", "");
				preference.setName(parser.parseUntil(new char[] {'='}, new char[] {'\n', '\r'}));
				parser.parseWhile(new char[] {'='});
				preference.setValue(parser.parseUntil(new char[] {'\n', '\r'}, new char[] {}));
				add(preference);
				parser.next();
			}
			parser.closeFile();
		}
		catch (FileNotFoundException e)
		{
			System.err.println("File not found. \"" + pathname + "\"");
		}
	}
	
	public void save()
	{
		FileParser parser;
		try
		{
			parser = new FileParser(pathname);
			for (int i = 0; i < size(); i++)
				parser.writeString(get(i).getName() + "=" + get(i).getValue() + "\n");
			parser.closeFile();
		}
		catch (FileNotFoundException e)
		{
			System.err.println("File not found. \"" + pathname + "\"");
		}
	}
	
	@Override
	public XMLElement toXML(String tagName)
	{
		XMLElement preference;
		XMLElement preferences = new XMLElement(tagName);
		for (Preference p : this)
		{
			preference = new XMLElement("Preference");
			preference.add(new XMLElement("Name", p.getName()));
			preference.add(new XMLElement("Value", p.getValue()));
			preferences.add(preference);
		}
		return preferences;
	}
	
	@Override
	public void fromXML(XMLElement element)
	{
		clear();
		
		for (XMLElement e : element.getChildElements())
		{
			if (e.getTagName().equals("Preference"))
			{
				this.add(new Preference(e.findElement("Name").getContent(), e.findElement("Value").getContent()));
			}
		}
	}
	
	public String getPathname()
	{
		return pathname;
	}
	
	public void setPathname(String pathname)
	{
		this.pathname = pathname;
	}
}
