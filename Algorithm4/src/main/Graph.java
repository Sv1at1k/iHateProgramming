package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Graph {
	private String url;
	private Map<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
	int salary;

	public Graph(String url) {
		this.url = url;

	}

	public void getSalary() {
		readAndParseData();
		printGraph();
		calculateSalary();
		System.out.println("Salary is" + salary);

	}

	private int calculateSalary() {
		salary = 0;
		for (Entry<Integer, ArrayList<Integer>> entry : graph.entrySet()) {
			List<Integer> slaims = new ArrayList<Integer>();
			int manager = entry.getKey();
			slaims.add(manager);

			while (!slaims.isEmpty()) {

				if (graph.get(manager).isEmpty()) {

					salary++;
				}
				for (Integer slaim : graph.get(manager)) {

					slaims.add(slaim);

				}
				slaims.remove(0);
				if (!slaims.isEmpty()) {
					manager = slaims.get(0);

				}
			}

		}
		return salary;

	}

	private void readAndParseData() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(url));
			String readString;
			int manager = 0;
			try {
				while ((readString = br.readLine()) != null) {
					if (!graph.containsKey(manager)) {
						graph.put(manager, new ArrayList<Integer>());

					}
					for (int slaim = 0; slaim < readString.length(); slaim++) {
						if (readString.charAt(slaim) == "Y".charAt(0)) {
							addNewConnection(manager, slaim);

						}

					}
					manager++;
				}
			} catch (IOException e) {
				System.out.println("Smth with output");
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}

	}

	private void addNewConnection(Integer manager, Integer slaim) {

		graph.get(manager).add(slaim);

	}

	private void printGraph() {
		for (Entry<Integer, ArrayList<Integer>> entry : graph.entrySet()) {
			System.out.print(entry.getKey() + ":");
			for (Integer slaim : entry.getValue()) {
				System.out.print(slaim + " ");

			}
			System.out.println();

		}

	}

}
