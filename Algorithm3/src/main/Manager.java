package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Manager {

	private int nodesAmount;
	private int connectionsAmount;
	private List<Integer> clients = new ArrayList<Integer>();
	private Graph graph;
	private Set<Integer> allNodes = new HashSet<Integer>();
	private List<Integer> serverList;

	public Manager(Graph g) {
		this.graph = g;

	}

	public void calculate() throws IOException {
		readAndParseData();

		serverList = new ArrayList<Integer>(allNodes);

		for (int i = 0; i < clients.size(); i++) {
			for (int j = 0; j < serverList.size(); j++) {
				if (serverList.get(j) == clients.get(i)) {
					serverList.remove(j);

				}

			}

		}

		int bestLatency = Integer.MAX_VALUE;

		for (int i = 0; i < serverList.size(); i++) {

			int worstDistanceToNodesFromServer = graph.calculateWorstDistance(graph.map, serverList.get(i));
			System.out.println("for server " + serverList.get(i) + " worst is " + worstDistanceToNodesFromServer);
			if (worstDistanceToNodesFromServer < bestLatency) {
				bestLatency = worstDistanceToNodesFromServer;

			}

		}
		System.out.println(bestLatency);

	}

	private void readAndParseData() throws IOException {
		String path = "E:\\Programing\\java\\Новая папка\\Hibernate\\Algorithm3\\src\\main\\gamsrv .in"; // path to file

		String[] nodesAndConnections = Files.readAllLines(Paths.get(path)).get(0).split(" "); // parse
																								// nodesAndConnections
		nodesAmount = Integer.parseInt(nodesAndConnections[0]);
		connectionsAmount = Integer.parseInt(nodesAndConnections[1]);

		String[] clientsArr = Files.readAllLines(Paths.get(path)).get(1).split(" "); // parse clients
		for (String str : clientsArr) {

			clients.add(Integer.parseInt(str));

		}

		String[] edgeData = null;

		Map<Integer, Node> nodes = new HashMap<Integer, Node>();

		for (int i = 2; i < connectionsAmount + 2; i++) { // parse edge data to object
			edgeData = Files.readAllLines(Paths.get(path)).get(i).split(" ");

			int node1Id = Integer.parseInt(edgeData[0]);
			int node2Id = Integer.parseInt(edgeData[1]);
			int distance = Integer.parseInt(edgeData[2]);

			if (!nodes.containsKey(node1Id) && !nodes.containsKey(node2Id)) {
				Node node1 = new Node(node1Id);
				Node node2 = new Node(node2Id);
				graph.addEdge(node1, node2, distance);
				nodes.put(node1Id, node1);
				nodes.put(node2Id, node2);

			} else if (nodes.containsKey(node1Id) && nodes.containsKey(node2Id)) {
				graph.addEdge(nodes.get(node1Id), nodes.get(node2Id), distance);

			} else if (nodes.containsKey(node1Id) && !nodes.containsKey(node2Id)) {
				Node node2 = new Node(node2Id);
				graph.addEdge(nodes.get(node1Id), node2, distance);
				nodes.put(node2Id, node2);

			} else if (!nodes.containsKey(node1Id) && nodes.containsKey(node2Id)) {
				Node node1 = new Node(node1Id);
				graph.addEdge(node1, nodes.get(node2Id), distance);
				nodes.put(node1Id, node1);

			}

		}
		for (Entry<Integer, Node> entry : nodes.entrySet()) {
			allNodes.add(entry.getKey());
		}

		{

		}

	}

	public boolean containsId(final List<Node> list, final Integer id) {
		return list.stream().map(Node::getId).filter(id::equals).findFirst().isPresent();
	}

}
