package calvert.dw.data.parsing;

/**
 * @author Duncan Calvert
 */
public class StringParser
{
	public static final char[]	NUMBERS				= {'0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9'										};
	
	public static final char[]	LOWERCASE_LETTERS	= {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	
	public static final char[]	UPPERCASE_LETTERS	= {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
			'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	
	public static final char[]	ALL_LETTERS			= {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	
	public static final char[]	RETURN_CHARS		= {'\n', '\r'};
	
	private int					pointer				= 0;
	private String				string;
	
	/**
	 * Creates a string parser.
	 * 
	 * @param string
	 */
	public StringParser(String string)
	{
		this.string = string;
	}
	
	/**
	 * Parses string until one of the break characters are encountered,
	 * returning
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
		for (int i = remainingChars(); i > 0; i--)
		{
			c = next();
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
		return parseUntil(breakChars, new char[] {});
	}
	
	/**
	 * Parses while valid chars.
	 * 
	 * @param validChars
	 */
	public String parseWhile(char... validChars)
	{
		char c;
		String content = "";
		Boolean valid;
		for (int i = remainingChars(); i > 0; i--)
		{
			c = next();
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
	
	public String parseRest()
	{
		String content = "";
		while (remainingChars() > 0)
		{
			content += next();
		}
		return content;
	}
	
	public String parseThrough(char c)
	{
		String content = parseUntil(c);
		increment(1);
		
		return content;
	}
	
	/**
	 * Skips until char is not a skipChar.
	 * 
	 * @param skipChars
	 */
	public void skipWhile(char[] skipChars)
	{
		Boolean skip = false;
		for (int i = remainingChars(); i > 0; i--)
		{
			char c = peek();
			for (char skipChar : skipChars)
			{
				if (c == skipChar)
				{
					skip = true;
					break;
				}
			}
			if (skip)
			{
				increment();
				skip = false;
			}
			else
				return;
		}
	}
	
	public void displayCodes()
	{
		for (int i = 0; i < getLength(); i++)
		{
			System.out.print((int) string.charAt(i) + " ");
		}
		System.out.print("\n");
	}
	
	public void skipWhile(char c)
	{
		skipWhile(new char[] {c});
	}
	
	public char next()
	{
		if (pointer >= string.length() || pointer < 0)
			return '\0';
		char c = string.charAt(pointer);
		increment();
		return c;
	}
	
	public String next(int i)
	{
		String str = "";
		for (int j = 0; j < i; j++)
		{
			str += next();
		}
		return str;
	}
	
	public String peekAt(int pos, int length)
	{
		int ptr = getPointer();
		seek(pos);
		String str = next(length);
		seek(ptr);
		return str;
	}
	
	public char peekAt(int i)
	{
		return string.charAt(i);
	}
	
	public String peek(int i)
	{
		String str = next(i);
		decrement(i);
		return str;
	}
	
	public char peek()
	{
		char c = next();
		decrement();
		return c;
	}
	
	public int remainingChars()
	{
		return getLength() - getPointer();
	}
	
	public void seek(int pos)
	{
		if (pos > string.length())
			pointer = string.length();
		else if (pos < 0)
			pointer = 0;
		else
			pointer = pos;
	}
	
	public void increment(int i)
	{
		pointer += i;
	}
	
	public void increment()
	{
		increment(1);
	}
	
	public void decrement(int i)
	{
		pointer -= i;
	}
	
	public void decrement()
	{
		decrement(1);
	}
	
	public int getLength()
	{
		return string.length();
	}
	
	public int getPointer()
	{
		return pointer;
	}
}
