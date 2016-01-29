/**
 * Transport task, finding shortest paths in a weighted graph by <a href="https://en.wikipedia.org/wiki/Floyd–Warshall_algorithm">the Floyd–Warshall algorithm</a>.
 */
package info.commuter;

import java.util.ArrayList;
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
		String space = " ";
		String gap = " : ";
		Integer integerMaxValue = Integer.valueOf(Integer.MAX_VALUE);
		// Floyd-Warshall algorithm here
		Map<Integer, List<Integer>> paths = (new FloydWarschall())
				.getShortestPaths(new FileActor((AddressBean) ctx.getBean("inputAddress")).getGraph());
		System.out.println("-------- implicit Java 8 traversal : shortest paths presentation ---------");
		paths.keySet().forEach(station -> {
			System.out.print(station.toString().concat(gap));
			paths.get(station).forEach(
					route -> System.out.print((route.equals(integerMaxValue) ? "-" : route).toString().concat(space)));
			System.out.println();
		});
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("----------- explicit traversal : shortest paths presentation ------------");
		List<Integer> stations = new ArrayList<Integer>();
		paths.keySet().forEach(stations::add);
		stations.sort((st1, st2) -> st1.compareTo(st2));
		for (Integer station : stations) {
			System.out.print(station.toString().concat(gap));
			paths.get(station).forEach(route -> System.out.print(route.toString().concat(space)));
			System.out.println();
		}
		System.out.println("-------------------------------------------------------------------------");
		//
		ctx.close();
	}
}