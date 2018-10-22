import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CowManager {
	private String file;
	private int cowsAmount;
	private int angryCows;
	List<Integer> cages = new ArrayList<Integer>();
	List<Integer> angryCages = new ArrayList<Integer>();
	List<Integer> distanceBetweenAngryCows = new ArrayList<Integer>();

	public void calculate() {
		setDataToCalculate(readFromFile());
		sort(cages);
		System.out.println("Sorted cages");
		for (Integer i : cages) {
			System.out.println(i);

		}

		setAngryCows();

		sort(angryCages);

		System.out.println("Cages for angry cows");

		for (Integer i : angryCages) {

			System.out.println(i);
		}

		getDistanceBetweenAngryCows();

		sort(distanceBetweenAngryCows);

		System.out.println("Answer is:");

		System.out.println(distanceBetweenAngryCows.get(1));

	}

	private void getDistanceBetweenAngryCows() {
		for (int i = 0; i < angryCages.size() - 1; i++) {
			distanceBetweenAngryCows.add(angryCages.get(i + 1) - angryCages.get(i));
		}

	}

	private void setAngryCows() {
		if (angryCows == 2) {
			angryCages.add(cages.get(0));
			angryCages.add(cages.get(cages.size() - 1));
			for (Integer i : angryCages) {
				System.out.println(i);
			}

		} else if (angryCows > 2) {
			angryCages.add(cages.get(0));
			angryCages.add(cages.get(cages.size() - 1));

			int numberOfSegments = cages.size() / (angryCows - 1);
			for (int i = numberOfSegments; i < cages.size() - 1; i = i + numberOfSegments) {
				if (angryCages.size() == angryCows) {
					break;
				}
				angryCages.add(cages.get(i));

			}

		} else {
			System.out.println("Місця достатньо");

		}

	}

	private void sort(List<Integer> list) {
		int listSize = list.size();
		for (int i = 0; i < listSize - 1; i++)
			for (int j = 0; j < listSize - i - 1; j++)
				if (list.get(j) > list.get(j + 1)) {
					Collections.swap(list, j, j + 1);
				}

	}

	private void setDataToCalculate(String data) {
		String[] dataArray = data.split(" ");
		this.cowsAmount = Integer.parseInt(dataArray[0]);
		this.angryCows = Integer.parseInt(dataArray[1]);
		for (int i = 2; i < dataArray.length; i++) {
			cages.add(Integer.parseInt(dataArray[i]));

		}

	}

	private String readFromFile() {
		String readedString = new String("");
		try (FileReader fileReader = new FileReader(file)) {
			Scanner scan = new Scanner(fileReader);

			while (scan.hasNextLine()) {
				readedString += "" + scan.nextLine();

			}

		} catch (FileNotFoundException e) {
			System.out.print("FIle Not Found");
		} catch (IOException e) {
			System.out.print("Something with output");

		}
		return readedString;

	}

	public CowManager(String file) {

		this.file = file;
	}

	public int getCowsAmount() {
		return cowsAmount;
	}

	public void setCowsAmount(int cowsAmount) {
		this.cowsAmount = cowsAmount;
	}

	public int getAngryCows() {
		return angryCows;
	}

	public void setAngryCows(int angryCows) {
		this.angryCows = angryCows;
	}

}