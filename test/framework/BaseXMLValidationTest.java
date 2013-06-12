package framework;

import static org.junit.Assert.assertEquals;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xml.resolver.tools.CatalogResolver;
import org.custommonkey.xmlunit.XMLUnit;
import org.custommonkey.xmlunit.XpathEngine;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

@RunWith(Parameterized.class)
public class BaseXMLValidationTest {
	protected String fileName;
	private static XpathEngine engine;
	public static DocumentBuilder builder;
	protected Document doc;
	
	public BaseXMLValidationTest(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * Setup the xpath engine, and build the document builder (which is used for parasing files into Documents)
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpEngine() throws Exception {
		XMLUnit.setControlParser("org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
		XMLUnit.setTestParser("org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
		XMLUnit.setSAXParserFactory("org.apache.xerces.jaxp.SAXParserFactoryImpl");
		XMLUnit.setTransformerFactory("org.apache.xalan.processor.TransformerFactoryImpl");
		XMLUnit.setControlEntityResolver(new CatalogResolver());
		engine = XMLUnit.newXpathEngine();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        dbf.setNamespaceAware(true);
        dbf.setIgnoringComments(false);
        dbf.setIgnoringElementContentWhitespace(false);
        dbf.setExpandEntityReferences(false);
        dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        builder = dbf.newDocumentBuilder();
	}
	
	@Before public void setUp() throws Exception {
		doc = BaseXMLValidationTest.builder.parse(fileName);
	}
	
	public XpathEngine getEngine() {
		return engine;
	}
	
	public DocumentBuilder getBuilder() {
		return builder;
	}
	
	/**
	 * Assert an xpath on the parsed document
	 * @param errorMessage - The error message to output when the assertion fails
	 * @param xpath - The xpath to assert is present
	 * @param expectedCount - The amount of elements the xpath is expected to return
	 * @throws Exception 
	 */
	public void assertXPath(String errorMessage, String xpath, int expectedCount) throws Exception {
		NodeList result = engine.getMatchingNodes(xpath, doc);
        assertEquals(errorMessage, expectedCount, result.getLength());
	}
}
