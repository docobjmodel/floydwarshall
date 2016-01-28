/**
 * Transport task, finding shortest paths in a weighted graph by <a href="https://en.wikipedia.org/wiki/Floyd–Warshall_algorithm">the Floyd–Warshall algorithm</a>.
 */
package info.commuter.configuration;

/**
 * @author fukubaru
 *
 *         The class provides adress either for data input or for output.
 */
public class AddressBean {
	/**
	 * the address
	 */
	private String address;

	/**
	 * @param address
	 *            the address value
	 */
	public AddressBean(String address) {
		this.address = address;
	}

	/**
	 * @return the address value
	 */
	public String getAddress() {
		return address;
	}
}