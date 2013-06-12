package dita;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import framework.BaseXMLValidationTest;

/**
 * Simple xpath test
 * @author sortiz
 */
@RunWith(Parameterized.class)
public class SimpleXPathTest extends BaseXMLValidationTest {
	
	public SimpleXPathTest(String fileName) {
		super(fileName);
	}

	/**
     * Validate images contain alt attribute
     */
    @Test public void testImagesContainAltAttribute() throws Exception {
    	assertXPath("Contains an <img> without an 'alt' attribute", "//image[not(@alt)]", 0);
    }
    
    @Parameters(name="{0}")
    public static Collection<Object[]> files() throws Exception {
    	Collection<Object[]> fileNames = new ArrayList<Object[]>();
    	
    	//Determine which directory to run tests on
    	Collection<File> files = FileUtils.listFiles(new File("samples"), new String[]{"xml", "ditamap"}, true);
    	String fileName = null;
    	
    	//Generate a list of filenames to run tests on
        for (Iterator<File> iterator = files.iterator(); iterator.hasNext();) {
            fileName = iterator.next().getAbsolutePath();
            fileNames.add( new String[] {fileName});
        }
    	return fileNames;
    }
}
