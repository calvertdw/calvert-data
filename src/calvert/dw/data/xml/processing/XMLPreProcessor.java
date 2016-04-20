package calvert.dw.data.xml.processing;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import calvert.dw.data.parsing.FileParser;
import calvert.dw.data.parsing.StringParser;
import calvert.dw.data.xml.parts.XMLAttribute;

public class XMLPreProcessor extends FileParser
{
	private ArrayList<XMLTag>	tags;
	
	public XMLPreProcessor(String pathname) throws FileNotFoundException
	{
		super(pathname);
	}
	
	public ArrayList<XMLTag> process()
	{
		tags = new ArrayList<>();
		gotoNext('<');
		decrement();
		while (remainingBytes() > 0)
			nextTag();
		cleanUp();
		return tags;
	}
	
	private void cleanUp()
	{
		for (XMLTag tag : tags)
			tag.setContent(formatContent(tag.getContent()));
	}
	
	private String formatContent(String content)
	{
		String formatted = "";
		StringParser p = new StringParser(content);
		p.skipWhile(new char[] {' ', '\r', '\n', '\t'});
		formatted += p.parseUntil(new char[] {}, new char[] {'\r', '\n', '\t'});
		return formatted;
	}
	
	private void nextTag()
	{
		String content = parseUntil(new char[] {'<'});
		if (tags.size() > 0)
			tags.get(tags.size() - 1).setContent(content);
		String type = parseWhile(new char[] {'<', '?', '/', '!', '-'});
		if (type.equals("<"))
			parseOpenTag(new XMLTag(XMLTag.OPEN));
		else if (type.equals("<?"))
			parseDeclarationTag(new XMLTag(XMLTag.DECLARATION));
		else if (type.equals("</"))
			parseCloseTag(new XMLTag(XMLTag.CLOSE));
		else if (type.equals("<!--"))
			parseCommentTag(new XMLTag(XMLTag.COMMENT));
		else
			return;
	}
	
	private void parseOpenTag(XMLTag tag)
	{
		tag.setTagName(parseUntil(new char[] {' ', '>'}));
		parseWhile(new char[] {' '});
		while (nextChar() != '>')
		{
			decrement();
			XMLAttribute e = new XMLAttribute(parseUntil(new char[] {'='}, new char[] {' '}));
			gotoNext('\"');
			e.setValue(parseUntil(new char[] {'\"'}));
			tag.addAttribute(e);
			parseUntil(new char[] {' ', '>'});
			parseWhile(new char[] {' '});
		}
		tags.add(tag);
	}
	
	private void parseDeclarationTag(XMLTag tag)
	{
		tag.setTagName(parseUntil(new char[] {' ', '?'}));
		parseWhile(new char[] {' '});
		while (nextChar() != '?')
		{
			decrement();
			XMLAttribute e = new XMLAttribute(parseUntil(new char[] {'='}, new char[] {' '}));
			gotoNext('\"');
			e.setValue(parseUntil(new char[] {'\"'}));
			tag.addAttribute(e);
			parseUntil(new char[] {' ', '?'});
			parseWhile(new char[] {' '});
		}
		increment();
		tags.add(tag);
	}
	
	private void parseCloseTag(XMLTag tag)
	{
		tag.setTagName(parseUntil(new char[] {'>'}, new char[] {' '}));
		increment();
		tags.add(tag);
	}
	
	private void parseCommentTag(XMLTag tag)
	{
		tag.setContent(parseUntil(new char[] {'>'}));
		increment();
		tags.add(tag);
	}
}
