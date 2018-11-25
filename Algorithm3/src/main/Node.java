package main;

import java.util.List;

public class Node {
	private int id;
	private int weigth;
	private boolean client = false;


	
	
		
	public Node(int id) {

		this.id = id;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWeigth() {
		return weigth;
	}

	public void setWeigth(int weigth) {
		this.weigth = weigth;
	}


	public boolean isClient() {
		return client;
	}


	public void setClient(boolean client) {
		this.client = client;
	}



}
