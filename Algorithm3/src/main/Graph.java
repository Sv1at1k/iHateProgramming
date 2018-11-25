package main;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.plaf.synth.SynthSplitPaneUI;

public class Graph {
	public Map<Node, ArrayList<Edge>> map = new HashMap<Node, ArrayList<Edge>>();

	public void addEdge(Node node1, Node node2, int distance) {
		Edge edge = new Edge(node1, node2, distance);
		addToMap(node1, edge);
		addToMap(node2, edge);

	}

	public List<Edge> getAdj(Node node) {
		return map.get(node);
	}

	private void addToMap(Node node, Edge edge) {
		if (map.containsKey(node)) {
			map.get(node).add(edge);

		} else if (!map.containsKey(node)) {
			map.put(node, new ArrayList<Edge>());
			map.get(node).add(edge);
		}
	}

	public int calculateWorstDistance(Map<Node, ArrayList<Edge>> map, int server) throws IOException {

		

		List<Node> unreviewed = new ArrayList<>();
		List<Node> reviewed = new ArrayList<>();

		Node currentNode = null;
		List<Integer> clients = new ArrayList<Integer>();
		String path = "E:\\Programing\\java\\Новая папка\\Hibernate\\Algorithm3\\src\\main\\gamsrv .in"; // path to file
		String[] clientsArr = Files.readAllLines(Paths.get(path)).get(1).split(" "); // parse clients
		for (String str : clientsArr) {
			clients.add(Integer.parseInt(str));

		}

		for (Entry<Node, ArrayList<Edge>> entry : map.entrySet()) {
			for (Integer i : clients) {
				if (entry.getKey().getId() == i) {
					entry.getKey().setClient(true);

				}

			}

			if (entry.getKey().getId() == server) {
				entry.getKey().setWeigth(0);
				currentNode = entry.getKey();
			} else {
				entry.getKey().setWeigth(999999);
			}
		}
		unreviewed.add(0, currentNode);
		int worstWeight = currentNode.getWeigth();

		while (reviewed.size() != map.size()) {
			currentNode = unreviewed.get(0);
			List<Edge> curentEdges = map.get(currentNode);
			for (Edge edge : curentEdges) {
				Node neightbour = edge.getAdjacentNode(currentNode);
				unreviewed.add(neightbour);
				if (neightbour.getWeigth() > currentNode.getWeigth() + edge.getDistance()
						&& unreviewed.contains(neightbour)) {
					neightbour.setWeigth(currentNode.getWeigth() + edge.getDistance());
				}

				if (neightbour.getWeigth() > worstWeight) {
					worstWeight = neightbour.getWeigth();

				}

			}
			unreviewed.remove(currentNode);
			reviewed.add(currentNode);

		}

		return worstWeight;
	}

}
