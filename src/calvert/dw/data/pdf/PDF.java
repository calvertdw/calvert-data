package calvert.dw.data.pdf;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PDF
{
	// PDF VERSION NUMBERS
	public static final String		VERSION_7	= "%PDF�1.7";
	public static final String		VERSION_6	= "%PDF�1.6";
	public static final String		VERSION_5	= "%PDF�1.5";
	public static final String		VERSION_4	= "%PDF�1.4";
	public static final String		VERSION_3	= "%PDF�1.3";
	public static final String		VERSION_2	= "%PDF�1.2";
	public static final String		VERSION_1	= "%PDF�1.1";
	public static final String		VERSION_0	= "%PDF�1.0";
	private static final String[]	VERSIONS	= {VERSION_0, VERSION_1, VERSION_2, VERSION_3, VERSION_4, VERSION_5, VERSION_6, VERSION_7};
	
	// ATTRIBUTES
	// private int version;
	
	// FILE STRUCTURE
	private String					header;
	
	public PDF()
	{
		this(7);
	}
	
	public PDF(int version)
	{
		// Warn if number out of range.
		if (version < 0 || version > 7)
			System.err.println("Version number out of range.");
		
		// Set file header to proper format.
		header = VERSIONS[version];
		
		// this.version = version;
		
	}
	
	public void write(File file)
	{
		try
		{
			FileWriter fw = new FileWriter(file, false);
			fw.write(header);
			fw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void write(String pathname)
	{
		write(new File(pathname));
	}
}
