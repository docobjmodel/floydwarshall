/**
 * Transport task, finding shortest paths in a weighted graph by <a href="https://en.wikipedia.org/wiki/Floyd–Warshall_algorithm">the Floyd–Warshall algorithm</a>.
 */
package info.commuter;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import info.algorithm.FloydWarschall;
import info.commuter.configuration.AddressBean;
import info.commuter.configuration.CommuterFileConfig;
import info.io.fileio.FileActor;

/**
 * @author fukubaru
 *
 */
public class CommuterApp {
	/**
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(CommuterFileConfig.class);
		ctx.refresh();
		// Floyd-Warshall algorithm here
		Map<Integer, List<Integer>> paths = (new FloydWarschall())
				.getShortestPaths(new FileActor((AddressBean) ctx.getBean("inputAddress")).getGraph());
		String space = " ";
		String gap = " : ";
		Integer integerMaxValue = Integer.valueOf(Integer.MAX_VALUE);
		System.out.println("-------- shortest paths presentation --------");
		paths.keySet().forEach(station -> {
			System.out.print(station.toString().concat(gap));
			paths.get(station).forEach(
					route -> System.out.print((route.equals(integerMaxValue) ? "-" : route).toString().concat(space)));
			System.out.println();
		});
		//
		ctx.close();
	}
}