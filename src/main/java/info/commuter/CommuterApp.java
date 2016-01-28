/**
 * Transport task, finding shortest paths in a weighted graph by <a href="https://en.wikipedia.org/wiki/Floyd–Warshall_algorithm">the Floyd–Warshall algorithm</a>.
 */
package info.commuter;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
		Map<String, List<Integer>> graph = (new FileActor((AddressBean) ctx.getBean("inputAddress"))).getGraph();
		// Floyd-Warshall algorithm here
		//
		ctx.close();
	}
}
