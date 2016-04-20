package calvert.dw.data.xml.processing;

import calvert.dw.data.xml.parts.XMLAttribute;
import calvert.dw.data.xml.parts.XMLDeclaration;
import calvert.dw.data.xml.parts.XMLElement;

public class XMLWriter
{
	public static String writeDeclaration(XMLDeclaration declaration)
	{
		String out = "";
		out += "<?";
		if (declaration.getDoctype() != null)
			out += declaration.getDoctype();
		else
			out += "xml";
		if (declaration.getVersion() != null)
		{
			out += " version=\"";
			out += declaration.getVersion();
			out += "\"";
		}
		if (declaration.getEncoding() != null)
		{
			out += " encoding=\"";
			out += declaration.getEncoding();
			out += "\"";
		}
		if (declaration.getStandalone() != null)
		{
			out += " standalone=\"";
			out += declaration.getStandalone();
			out += "\"";
		}
		out += "?>\n";
		return out;
	}
	
	// recursive
	public static String writeElement(XMLElement e, int indent)
	{
		String out = "";
		// begin <tagname att1="val1" att2="val2">
		out += indent(indent);
		out += "<";
		out += e.getTagName();
		out += writeAttributes(e);
		out += ">\n";
		// content
		if (e.getContent().length() > 0)
		{
			out += indent(indent + 1);
			out += e.getContent();
			out += "\n";
		}
		// child elements
		for (XMLElement child : e.getChildElements())
		{
			out += writeElement(child, indent + 1);
		}
		// closing <\tagname>
		out += indent(indent);
		out += "</";
		out += e.getTagName();
		out += ">";
		out += "\n";
		return out;
	}
	
	public static String writeAttributes(XMLElement e)
	{
		String out = "";
		for (XMLAttribute att : e.getAttributes())
		{
			out += " ";
			out += att.getName();
			out += "=\"";
			out += att.getValue();
			out += "\"";
		}
		return out;
	}
	
	public static String indent(int n)
	{
		String out = "";
		for (int i = 0; i < n; i++)
			out += "\t";
		return out;
	}
}
