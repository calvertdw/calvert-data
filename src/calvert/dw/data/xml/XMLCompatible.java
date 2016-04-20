package calvert.dw.data.xml;

import calvert.dw.data.xml.parts.XMLElement;

public interface XMLCompatible
{
	public XMLElement toXML(String tagName);
	
	public void fromXML(XMLElement element);
}
