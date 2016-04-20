package calvert.dw.data.xml.parts;

public class XMLDeclaration
{
	private String	doctype		= "";
	private String	version		= "1.0";
	private String	encoding	= "ASCII";
	private String	standalone	= "yes";
	
	/**
	 * Encoding values
	 * UTF-8, UTF-16, ISO-10646-UCS-2, ISO-10646-UCS-4, ISO-8859-1 to
	 * ISO-8859-9, ISO-2022-JP, Shift_JIS, EUC-JP
	 */
	
	public XMLDeclaration(String doctype)
	{
		this.doctype = doctype;
	}
	
	public String getDoctype()
	{
		return doctype;
	}
	
	public void setDoctype(String doctype)
	{
		this.doctype = doctype;
	}
	
	public String getVersion()
	{
		return version;
	}
	
	public void setVersion(String version)
	{
		this.version = version;
	}
	
	public String getEncoding()
	{
		return encoding;
	}
	
	public void setEncoding(String encoding)
	{
		this.encoding = encoding;
	}
	
	public String getStandalone()
	{
		return standalone;
	}
	
	public void setStandalone(String standalone)
	{
		this.standalone = standalone;
	}
}
