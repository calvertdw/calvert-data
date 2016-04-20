package calvert.dw.data.pdf;

import org.junit.Test;

import calvert.dw.data.pdf.objects.PDFIndirectObject;
import calvert.dw.data.pdf.objects.PDFStreamObject;

public class PDFTest
{
	@Test
	public void testInitializationException()
	{
		new PDF(8);
	}
	
	@Test
	public void testStream()
	{
		PDFIndirectObject obj = new PDFIndirectObject(5);
		PDFStreamObject stream = new PDFStreamObject();
		stream.appendStream("BT\n(Hello) Tj\nET");
		
		obj.setContent(stream);
		System.out.println(obj.toString());
	}
}
