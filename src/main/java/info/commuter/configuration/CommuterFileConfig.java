/**
 * Transport task, finding shortest paths in a weighted graph by <a href="https://en.wikipedia.org/wiki/Floyd–Warshall_algorithm">the Floyd–Warshall algorithm</a>.
 */
package info.commuter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fukubaru This class provides {@link java.net.URL URL} of input data
 *         source for {@link org.springframework.context.annotation.Profile
 *         Profile} for the case of file input-output.
 */
@Configuration
public class CommuterFileConfig implements DataAddressor {
	/**
	 * @see DataAddressor#inputAddress()
	 */
	@Bean(name = "inputAddress")
	@Override
	public AddressBean inputAddress() {
		return new AddressBean("resources/fileio/graph.input.txt");
	}

	/**
	 * @see DataAddressor#outputAddress()
	 */
	@Bean(name = "outputAddress")
	@Override
	public AddressBean outputAddress() {
		return new AddressBean("resources/fileio/graph.output.txt");
	}
}