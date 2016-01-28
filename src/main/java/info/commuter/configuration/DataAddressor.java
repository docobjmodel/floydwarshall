/**
 * Transport task, finding shortest paths in a weighted graph by <a href="https://en.wikipedia.org/wiki/Floyd–Warshall_algorithm">the Floyd–Warshall algorithm</a>.
 */
package info.commuter.configuration;

/**
 * @author fukubaru This interface provides {@link java.lang.String addresses}
 *         of input and output data. They could be files, URL instances, URL of
 *         database, an so forth.
 */
public interface DataAddressor {
	/**
	 * @return {@link info.commuter.configuration.AddressBean address} of input
	 *         data file
	 */
	public AddressBean inputAddress();

	/**
	 * @return {@link info.commuter.configuration.AddressBean address} of target
	 *         output data file
	 */
	public AddressBean outputAddress();
}