package calvert.dw.data.pdf.objects;

import org.junit.Test;

public class PDFHexidecimalObjectTest
{
	@Test
	public void testHex()
	{
		System.out.println(PDFHexidecimalObject.toHexString(new byte[] {0, 1, (byte) 255, 4}));
	}
}
