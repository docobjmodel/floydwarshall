/**
 * Transport task, finding shortest paths in a weighted graph by <a href="https://en.wikipedia.org/wiki/Floyd–Warshall_algorithm">the Floyd–Warshall algorithm</a>.
 */
package info.commuter.configuration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author fukubaru This class corrects of input / output addresses.
 */
public class TestFileConfig {
	private AnnotationConfigApplicationContext ctx;
	/**
	 * instance of the input file's
	 * {@link info.commuter.configuration.AddressBean address}
	 */
	private AddressBean inputAddress;

	/**
	 * The annotated method will be run only once before the first test method
	 * in the current class is invoked.
	 */
	@BeforeClass
	public void setUp() {
		ctx = new AnnotationConfigApplicationContext();
		ctx.register(CommuterFileConfig.class);
		ctx.refresh();
		inputAddress = (AddressBean) ctx.getBean("inputAddress");
	}

	@AfterClass
	public void shutDown() {
		ctx.close();
	}

	/**
	 * test validity of the input file's {@link java.net.URL URL}
	 */
	@Test(groups = { "fileio", "configuration", "common" })
	public void testInputAddress() {
		assert inputAddress != null : "input URL is null";
		System.out.println(getClass().getCanonicalName().concat("#testInputAddress").concat("\ninput address ")
				.concat(inputAddress.getAddress()));
	}
}