package graphs;

import java.util.*;

/**
 * Implements a graph. We use two maps: one map for adjacency properties
 * (adjancencyMap) and one map (dataMap) to keep track of the data associated
 * with a vertex.
 * 
 * @author cmsc132
 * 
 * @param <E>
 */
public class Graph<E> {
	/* You must use the following maps in your implementation */
	private HashMap<String, HashMap<String, Integer>> adjacencyMap;
	private HashMap<String, E> dataMap;

	public Graph() {
		adjacencyMap = new HashMap<String, HashMap<String, Integer>>();
		dataMap = new HashMap<String, E>();
	}

	public void addDirectedEdge(String startVertexName, String endVertexName, int cost) {

		//TODO can I assume that the adjacency map will always have the 
		//the startVertex?
		HashMap<String, Integer> map = null;
		if (adjacencyMap.containsKey(startVertexName)) {
			map = adjacencyMap.get(startVertexName);
			map.put(endVertexName, cost);
		}
	}

	// TODO do I need to reuse code between BFS and DFS. Also just realized I could
	// have used a LinkedList(push & pop) for DFS instead of stack. Does is matter.
	public void doBreadthFirstSearch(String startVertexName, CallBack<E> callBack) {
		LinkedList<String> queue = new LinkedList<String>();
		Set<String> visited = new HashSet<String>();
		queue.offer(startVertexName);
		while (!queue.isEmpty()) {
			String vertex = queue.poll();
			if (!visited.contains(vertex)) {
				visited.add(vertex);
				callBack.processVertex(vertex, dataMap.get(vertex));
				HashMap<String, Integer> successors = adjacencyMap.get(vertex);
				// TODO is this treemap correct?
				TreeMap<String, Integer> sortedSuccessors = new TreeMap<String, Integer>(successors);
				Set<String> successorKeys = sortedSuccessors.keySet();
				for (String successor : successorKeys) {
					if (!visited.contains(successor)) {
						queue.offer(successor);
					}
				}

			}
		}

	}

	public void doDepthFirstSearch(String startVertexName, CallBack<E> callBack) {
		Stack<String> stack = new Stack<String>();
		Set<String> visited = new HashSet<String>();
		stack.push(startVertexName);
		while (!stack.isEmpty()) {
			String vertex = stack.pop();
			if (!visited.contains(vertex)) {
				visited.add(vertex);
				callBack.processVertex(vertex, dataMap.get(vertex));
				HashMap<String, Integer> successors = adjacencyMap.get(vertex);
				// TODO is this treemap correct?
				TreeMap<String, Integer> sortedSuccessors = new TreeMap<String, Integer>(successors);
				Set<String> successorKeys = sortedSuccessors.keySet();
				for (String successor : successorKeys) {
					if (!visited.contains(successor)) {
						stack.push(successor);
					}
				}

			}
		}

	}

	public void addVertex(String vertexName, E value) {
		dataMap.put(vertexName, value);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		adjacencyMap.put(vertexName, map);

	}

	public String toString() {
		// Build the vertices
		StringBuilder build = new StringBuilder("Vertices: [");
		TreeMap<String, E> sortedVertices = new TreeMap<String, E>(dataMap);
		Set<String> vertexKeys = sortedVertices.keySet();
		Iterator<String> iterator = vertexKeys.iterator();
		// do first one outside the loop to account for the comma
		// TODO is this a good approach?
		if (iterator.hasNext()) {
			build.append(iterator.next());
		}
		while (iterator.hasNext()) {
			String vertex = (String) iterator.next();
			build.append(", ").append(vertex);
		}
		build.append("]");

		// Build the edges
		build.append("\n").append("Edges:").append("\n");
		for (String vertex : vertexKeys) {
			build.append("Vertex(").append(vertex).append(")").append("--->");
			HashMap<String, Integer> map = adjacencyMap.get(vertex);
			iterator = map.keySet().iterator();
			build.append("{");
			String key = "";
			// do first one outside the loop to account for the comma
			if (iterator.hasNext()) {
				key = (String) iterator.next();
				build.append(key).append("=").append(map.get(key));
			}
			while (iterator.hasNext()) {
				key = (String) iterator.next();
				build.append(", ").append(key).append("=").append(map.get(key));
			}
			build.append("}").append("\n");
		}

		return build.toString();
	}

	public Set<String> getVertices() {
		return dataMap.keySet();
	}

	public int doDijkstras(String startVertex, String endVertex, ArrayList<String> shortestPath) {
		//if the start end end vertex are equal, then we know the cost is 0
		//and the path is the startVertex
		if (startVertex.equals(endVertex)) {
			shortestPath.add(startVertex);
			return 0;
		}
		
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		HashMap<String, Integer> costMap = new HashMap<String, Integer>();
		HashMap<String, String> predecessorMap = new HashMap<String, String>();
		Set<String> visited = new HashSet<String>();

		initialize(startVertex, costMap, predecessorMap);

		queue.offer(new Node(startVertex, 0));
		//TODO
		//is it OK to use this as my loop? The algorithm says
		//while nodes can be added to S
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			String vertex = node.getNode();
			visited.add(vertex);
			HashMap<String, Integer> adjacents = adjacencyMap.get(vertex);
			Set<String> adjacentKeys = adjacents.keySet();
			for (String adjNode : adjacentKeys) {
				if (!visited.contains(adjNode)) {
					int nodeCost = costMap.get(vertex);
					int adjNodeCost = adjacents.get(adjNode);
					int newCost = nodeCost + adjNodeCost;
					if (newCost < costMap.get(adjNode)) {
						costMap.put(adjNode, newCost);
						predecessorMap.put(adjNode, vertex);
					}
					queue.offer(new Node(adjNode, newCost));
				}
			}
		}
		//if no path is found in calculating the shortest path,
		//Return "None" and -1; otherwise return the shortest path.
		getShortestPath(endVertex, startVertex, predecessorMap, shortestPath);
		if (shortestPath.get(0).equals("None") && shortestPath.size() == 1) {
			return -1;
		}
		return costMap.get(endVertex);
	}

	private void getShortestPath(String endVertex, String startVertex, HashMap<String, String> predecessorMap,
			ArrayList<String> shortestPath) {
		String predecessor = predecessorMap.get(endVertex);
		//if the predecessor of a node in our path has never been visited
		//we know there is an invalid path.
		if (predecessor.equals("None")) {
			shortestPath.add("None");
			return;
		}
		
		shortestPath.add(endVertex);
		shortestPath.add(predecessor);
		while (predecessor != startVertex) {
			predecessor = predecessorMap.get(predecessor);
			shortestPath.add(predecessor);
		}
		Collections.reverse(shortestPath);
	}

	private void initialize(String startVertex, HashMap<String, Integer> costMap,
			HashMap<String, String> predecessorMap) {
		Integer infinity = Integer.MAX_VALUE;
		Set<String> vertexKeys = adjacencyMap.keySet();
		for (String vertex : vertexKeys) {
			costMap.put(vertex, infinity);
			predecessorMap.put(vertex, "None");
		}
		costMap.put(startVertex, 0);

	}

	public int getCost(String startVertexName, String endVertexName) {
		Map<String, Integer> map = adjacencyMap.get(startVertexName);
		return map.get(endVertexName);
	}
}
