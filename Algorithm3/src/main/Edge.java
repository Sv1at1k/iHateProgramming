package main;

public class Edge {
	private Node node1, node2;
	private int distance;

	public Edge(Node node1, Node node2, int distance) {
		this.node1 = node1;
		this.node2 = node2;
		this.distance = distance;
	}

	public Node getAdjacentNode(Node node) {
		if (node.getId() == this.node1.getId()) {
			return node2;

		} else {

			return node1;

		}

	}

	public int getDistance() {

		return distance;
	}
	
	public Node getNode1() {

		return node1;
	}
	
	public Node getNode2() {

		return node2;
	}
}
