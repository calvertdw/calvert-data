package calvert.dw.data.parsing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author Duncan Calvert
 */
public class FileParser extends RandomAccessFile
{
	/**
	 * Creates a parse file with pathname.
	 * 
	 * @param pathname
	 * @throws FileNotFoundException
	 */
	public FileParser(String pathname) throws FileNotFoundException
	{
		super(new File(pathname), "rws");
	}
	
	/**
	 * Parses file until one of the break characters are encountered, returning
	 * a string of all characters that are not ignored.
	 * 
	 * @param breakChars
	 * @param ignoreChars
	 * @return content
	 */
	public String parseUntil(char[] breakChars, char[] ignoreChars)
	{
		char c;
		String content = "";
		Boolean ignoreSwitch;
		for (long i = remainingBytes(); i > 0; i--)
		{
			c = nextChar();
			for (char breakChar : breakChars)
			{
				if (breakChar == c)
				{
					decrement();
					return content;
				}
			}
			ignoreSwitch = false;
			for (char ignoreChar : ignoreChars)
			{
				if (c == ignoreChar)
				{
					ignoreSwitch = true;
					break;
				}
			}
			if (!ignoreSwitch)
				content += String.valueOf(c);
		}
		return content;
	}
	
	public String parseUntil(char... breakChars)
	{
		char[] ignoreChars = {};
		return parseUntil(breakChars, ignoreChars);
	}
	
	public String parseUntilAndSkipOne(char... breakChars)
	{
		String value = parseUntil(breakChars);
		increment();
		return value;
	}
	
	/**
	 * Parses while valid chars.
	 * 
	 * @param validChars
	 */
	public String parseWhile(char[] validChars)
	{
		char c;
		String content = "";
		Boolean valid;
		for (long i = remainingBytes(); i > 0; i--)
		{
			c = nextChar();
			valid = false;
			for (char validChar : validChars)
			{
				if (c == validChar)
				{
					valid = true;
					break;
				}
			}
			if (valid)
				content += String.valueOf(c);
			else
			{
				decrement();
				return content;
			}
		}
		return "";
	}
	
	/**
	 * Skips until char is not a skipChar.
	 * 
	 * @param skipChars
	 */
	public void skipWhile(char[] skipChars)
	{
		Boolean skip;
		for (long i = remainingBytes(); i > 0; i--)
		{
			skip = false;
			for (char skipChar : skipChars)
			{
				if (skipChar == nextChar())
				{
					skip = true;
					break;
				}
			}
			if (!skip)
			{
				decrement();
				return;
			}
		}
	}
	
	/**
	 * Skips until char is not skipChar.
	 * 
	 * @param skipChar
	 */
	public void skipWhile(char skipChar)
	{
		for (long i = remainingBytes(); i > 0; i--)
		{
			if (skipChar != nextChar())
			{
				decrement();
				return;
			}
		}
	}
	
	/**
	 * Goes to next character in file that matches search character.
	 * 
	 * @param searchChar
	 */
	public void gotoNext(char searchChar)
	{
		for (long i = remainingBytes(); i > 0; i--)
		{
			if (searchChar == next())
				return;
		}
	}
	
	public long remainingBytes()
	{
		return getLength() - getPosition();
	}
	
	public long getPosition()
	{
		try
		{
			return getFilePointer();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return getLength();
		}
	}
	
	public long getLength()
	{
		try
		{
			return length();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public int next()
	{
		try
		{
			return read();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public char nextChar()
	{
		return (char) next();
	}
	
	public char peekChar()
	{
		char c = (char) next();
		decrement();
		return c;
	}
	
	public void increment(int i)
	{
		setPosition(getPosition() + i);
	}
	
	public void increment()
	{
		increment(1);
	}
	
	public void decrement(int i)
	{
		setPosition(getPosition() - i);
	}
	
	public void decrement()
	{
		decrement(1);
	}
	
	public void setPosition(long pos)
	{
		try
		{
			seek(pos);
		}
		catch (IOException e)
		{
			// e.printStackTrace();
		}
	}
	
	public void clear()
	{
		try
		{
			setLength(0);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void closeFile()
	{
		try
		{
			close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void writeString(String s)
	{
		try
		{
			writeBytes(s);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
