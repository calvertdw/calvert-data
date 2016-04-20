package calvert.dw.data.lineCounting;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class LineCounter
{
	public static int countLinesJava(String pathname)
	{
		return countLinesJava(new File(pathname));
	}
	
	private static int countLinesJava(File file)
	{
		if (file.isFile() && String.valueOf(getFileType(file.getName())).equals("hbm.xml"))
		{
			FileReader reader;
			
			int sum = 0;
			try
			{
				reader = new FileReader(file);
				
				for (int c = reader.read(); c >= 0; c = reader.read())
				{
					if (c == 10 || c == 13)
						sum++;
				}
				
				reader.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			return sum;
		}
		else if (file.isDirectory() && file.getName().charAt(0) != '.')
		{
			int sum = 0;
			for (File subFile : file.listFiles())
			{
				sum += countLinesJava(subFile);
			}
			
			return sum;
		}
		return 0;
	}
	
	public static char[] getFileType(String fileName)
	{
		int pos;
		for (pos = 0; pos < fileName.length() - 1; pos++)
		{
			if (fileName.charAt(pos) == '.')
				break;
		}
		
		if (pos == fileName.length() - 1)
			return new char[] {' '};
		
		char[] type = new char[fileName.length() - pos - 1];
		fileName.getChars(pos + 1, fileName.length(), type, 0);
		
		return type;
	}
	
	public static void main(String[] args)
	{
		int sum = 0;
		
		long t1 = new Date().getTime();
		
// sum += countLinesJava("c:/Users/Duncan/Eclipse/workspace-java/CalvertData");
// sum += countLinesJava("c:/Users/Duncan/Eclipse/workspace-java/CalvertGui");
// sum += countLinesJava("c:/Users/Duncan/Eclipse/workspace-java/CalvertMath");
// sum += countLinesJava("c:/Users/Duncan/Eclipse/workspace-java/CalvertTime");
// sum += countLinesJava("c:/Users/Duncan/Eclipse/workspace-java/CalvertTools");
// sum += countLinesJava("c:/Users/Duncan/Eclipse/workspace-java/CalvertVision");
// sum += countLinesJava("c:/Users/Duncan/Eclipse/workspace-java/Scheduling2");
// sum += countLinesJava("c:/Users/Duncan/Eclipse/workspaceWeb2/WebITS/src/edu/uwf/cs/webits/server");
		sum += countLinesJava("c:/Users/Duncan/Eclipse/workspaceWeb2/WebIntelligentTutoringSystem/src/edu/uwf/cs/webits/server");
		
		long t2 = new Date().getTime();
		
		System.out.println("Lines: " + sum);
		System.out.println("Time: " + (t2 - t1));
	}
}
