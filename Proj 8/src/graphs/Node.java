
package graphs;

public class Node implements Comparable<Node> {

	private String node;
	private int cost;

	public Node(String node, int cost) {
		this.node = node;
		this.cost = cost;
	}

	public String getNode() {
		return node;
	}

	public int getCost() {
		return cost;
	}

	@Override
	public int compareTo(Node p) {
		if (p != null && getClass() == p.getClass()) {
			if (this.cost == p.cost) {
				return 0;
			} else if (this.cost < p.cost) {
				return -1;
			} else {
				return 1;
			}
		}
		return 0;
	}

	public String toString() {
		return "node: " + node;
	}
}
