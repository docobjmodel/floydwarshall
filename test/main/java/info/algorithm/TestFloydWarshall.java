/**
 * Transport task, finding shortest paths in a weighted graph by <a href="https://en.wikipedia.org/wiki/Floyd–Warshall_algorithm">the Floyd–Warshall algorithm</a>.
 */
package info.algorithm;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import info.commuter.configuration.AddressBean;
import info.commuter.configuration.CommuterFileConfig;
import info.io.fileio.FileActor;

/**
 * @author fukubaru The class tests the Floyd-Warshall algorithm.
 */
public class TestFloydWarshall {
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
	 * tests validity of the algorithm {@link java.net.URL URL}
	 */
	@Test(groups = { "common" })
	public void testFloydWarshall() {
		Map<Integer, List<Integer>> paths = (new FloydWarschall())
				.getShortestPaths(new FileActor(inputAddress).getGraph());
		String space = " ";
		String gap = " : ";
		Integer integerMaxValue = Integer.valueOf(Integer.MAX_VALUE);
		paths.keySet().forEach(station -> {
			System.out.print(station.toString().concat(gap));
			paths.get(station).forEach(
					route -> System.out.print((route.equals(integerMaxValue) ? "-" : route).toString().concat(space)));
			System.out.println();
		});
	}
}