/**
 * Transport task, finding shortest paths in a weighted graph by <a href="https://en.wikipedia.org/wiki/Floyd�Warshall_algorithm">the Floyd�Warshall algorithm</a>.
 */
package info.io.fileio;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import info.commuter.configuration.AddressBean;
import info.commuter.configuration.CommuterFileConfig;

/**
 * @author fukubaru This class tests validity of input reading.
 */
public class TestFileActor {
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
	 * tests validity of the input file's address and reading of the content
	 */
	@Test(groups = { "fileio", "configuration", "common" })
	public void testInputData() {
		String gap = " : ";
		String space = " ";
		Integer integerMaxValue = Integer.valueOf(Integer.MAX_VALUE);
		Map<Integer, List<Integer>> graph = new FileActor(inputAddress).getGraph();
		graph.keySet().forEach(station -> {
			System.out.print(station.toString().concat(gap));
			graph.get(station).forEach(
					route -> System.out.print((route.equals(integerMaxValue) ? "-" : route).toString().concat(space)));
			System.out.println();
		});
	}
}