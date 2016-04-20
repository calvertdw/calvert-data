package calvert.dw.data.xml;

public class XMLPreferences
{
	/**
	 * Gets a single value from XML file.
	 * 
	 * @param name
	 * @return
	 */
	public static String getPreference(String name)
	{
		XMLDocument prefDoc = new XMLDocument("preferences.xml");
		prefDoc.load();
		return prefDoc.getRoot().findElement(name).getContent();
	}
	
	/**
	 * Gets an array of values from XML file.
	 * 
	 * @param names
	 * @return
	 */
	public static String[] getPreferences(String[] names)
	{
		String[] values = new String[names.length];
		XMLDocument prefDoc = new XMLDocument("preferences.xml");
		prefDoc.load();
		for (int i = 0; i < names.length; i++)
			values[i] = prefDoc.getRoot().findElement(names[i]).getContent();
		return values;
	}
	
	/**
	 * Sets a single value in XML file.
	 * 
	 * @param name
	 * @param value
	 */
	public static void setPreference(String name, String value)
	{
		XMLDocument prefDoc = new XMLDocument("preferences.xml");
		prefDoc.load();
		prefDoc.getRoot().findElement(name).setContent(value);
		prefDoc.save();
	}
	
	/**
	 * Sets a group of preferences in an XML file.
	 * 
	 * @param names
	 * @param values
	 */
	public static void setPreferences(String[] names, String[] values)
	{
		XMLDocument prefDoc = new XMLDocument("preferences.xml");
		prefDoc.load();
		for (int i = 0; i < names.length; i++)
			prefDoc.getRoot().findElement(names[i]).setContent(values[i]);
		prefDoc.save();
	}
	
	public static int getIndexOf(String name, String[] fields)
	{
		for (int i = 0; i < fields.length; i++)
			if (name.equals(fields[i]))
				return i;
		return 0;
	}
}
