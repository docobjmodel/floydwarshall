/**
 * Transport task, finding shortest paths in a weighted graph by <a href="https://en.wikipedia.org/wiki/Floyd–Warshall_algorithm">the Floyd–Warshall algorithm</a>.
 */
package info.algorithm;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * @author fukubaru
 *
 *         The class provides Floyd-Warshall algorithm
 */
public class FloydWarschall {
	/**
	 * service variable
	 */
	private int m;
	/**
	 * service variable
	 */
	private int n;

	/**
	 * @param graph
	 *            initial graph
	 * @return matrix of shortest paths values between each vertex pairs, if
	 *         exists
	 */
	public Map<Integer, List<Integer>> getShortestPaths(Map<Integer, List<Integer>> graph) {
		// n <- rows[W]
		int size = graph.size();
		Integer[][] dist = new Integer[size][size];
		// prepare the initial matrix copy of the graph D <- W
		List<Integer> stations = new ArrayList<Integer>();
		graph.keySet().forEach(stations::add);
		stations.sort((st1, st2) -> st1.compareTo(st2));
		m = 0;
		for (Integer station : stations) {
			n = 0;
			graph.get(station).forEach(route -> {
				dist[m][n] = route;
				n++;
			});
			m++;
		}

		// algorithm itself
		// for k <- 1 to n
		Integer integerMaxValue = Integer.valueOf(Integer.MAX_VALUE);
		for (int k = 0; k < size; k++) {
			// for i <- 1 to n
			for (int i = 0; i < size; i++) {
				// for j <- 1 to n
				for (int j = 0; j < size; j++) {
					// if dist[i][j] > dist[i][k] + dist[k][j]
					// dist[i][j] <- dist[i][k] + dist[k][j]
					if (!dist[i][k].equals(integerMaxValue) && !dist[k][j].equals(integerMaxValue)) {
						int sum = dist[i][k].intValue() + dist[k][j].intValue();
						if (dist[i][j].equals(integerMaxValue) || dist[i][j].intValue() > sum) {
							dist[i][j] = Integer.valueOf(sum);
						}
					}
				}
			}
		}

		// copy the values to result Map
		m = 0;
		Map<Integer, List<Integer>> paths = new Hashtable<Integer, List<Integer>>();
		for (Integer station : stations) {
			List<Integer> routes = new ArrayList<Integer>();
			for (n = 0; n < size; n++) {
				routes.add(dist[m][n]);
			}
			paths.put(station, routes);
			m++;
		}
		return paths;
	}
}