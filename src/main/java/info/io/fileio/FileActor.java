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
	private Map<String, List<Integer>> graph;

	/**
	 * @param inputLocation
	 *            bean with the {@link AddressBean location} of the input file
	 */
	public FileActor(AddressBean inputLocation) {
		try {
			graph = new Hashtable<String, List<Integer>>();
			Properties props = new Properties();
			props.load(new FileInputStream(inputLocation.getAddress()));
			Set<Object> keys = props.keySet();
			String comma = ",";
			String line;
			String station;
			List<Integer> weights;
			Integer integerMaxValue = Integer.valueOf(Integer.MAX_VALUE);
			for (Object key : keys) {
				station = (String) key;
				weights = new ArrayList<Integer>();
				line = props.getProperty(station);
				String[] routes = line.split(comma);
				for (String route : routes) {
					weights.add(route.equals("-") ? integerMaxValue : Integer.valueOf(route));
				}
				graph.put(station, weights);
			}
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
	public Map<String, List<Integer>> getGraph() {
		return graph;
	}
}