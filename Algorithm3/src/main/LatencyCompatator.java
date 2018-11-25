package main;

import java.util.Comparator;

public class LatencyCompatator implements Comparator<Node> {

	@Override
	public int compare(Node o1, Node o2) {
		if(o1.getWeigth() == o2.getWeigth()) {
			return 0;
			
			
		}
		if(o1.getWeigth() > o2.getWeigth()) {
			
			return -1;
			
		}
		if(o1.getWeigth() < o2.getWeigth()) {
			
			return 1;
		}
		return 0;
		
	}
	

}
