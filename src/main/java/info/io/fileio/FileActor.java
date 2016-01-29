/**
 * Transport task, finding shortest paths in a weighted graph by <a href="https://en.wikipedia.org/wiki/Floyd–Warshall_algorithm">the Floyd–Warshall algorithm</a>.
 */
package info.io.fileio;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import info.commuter.configuration.AddressBean;

/**
 * @author fukubaru This class provides input and output methods for the file
 *         based data representation.
 */
public class FileActor {
	/**
	 * adjacency matrix of the directed graph - graph edges
	 */
	private Map<Integer, List<Integer>> graph;

	/**
	 * @param inputLocation
	 *            bean with the {@link AddressBean location} of the input file
	 */
	public FileActor(AddressBean inputLocation) {
		try {
			graph = new Hashtable<Integer, List<Integer>>();
			Properties props = new Properties();
			props.load(new FileInputStream(inputLocation.getAddress()));
			Set<Object> keys = props.keySet();
			List<Integer> vertices = new ArrayList<Integer>();
			keys.forEach(vertex -> vertices.add(Integer.valueOf(vertex.toString())));
			String comma = ",";
			Integer integerMaxValue = Integer.valueOf(Integer.MAX_VALUE);
			vertices.forEach(vertex -> {
				List<Integer> weights = new ArrayList<Integer>();
				String line = props.getProperty(vertex.toString());
				String[] routes = line.split(comma);
				for (String route : routes) {
					weights.add(route.equals("-") ? integerMaxValue : Integer.valueOf(route));
				}
				graph.put(vertex, weights);
			});
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return adjacency matrix of the directed graph, see <a href=
	 *         "http://www.mif.vu.lt/~valdas/ALGORITMAI/LITERATURA/Cormen/Cormen.pdf">
	 *         Section_22.1 Representations of graphs</a>. Keys are names of the
	 *         stations, and values are ordered lists of route weights for each
	 *         station respectively other stations, route for itself is zero.
	 */
	public Map<Integer, List<Integer>> getGraph() {
		return graph;
	}
}