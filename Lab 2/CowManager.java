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
	String[] data;

	public Integer calculate() {
		String[] data = readFromFile().split(" ");
		Integer[] corralNumber = setDataToCalculate(data);
		sortAvailableCorrals(corralNumber);
		List<Integer> suitableCorralsList = filter(corralNumber);
		List<Integer> distanceBetweenAngryCowsList = getDistanceBetweenAngryCows(suitableCorralsList);
		return (Integer) sortDistanceBetweenAngryCows(distanceBetweenAngryCowsList).get(0);
	}

	private Integer[] setDataToCalculate(String[] data) {
		Integer[] corralNumber = new Integer[data.length - 2];
		setCowsAmount(Integer.parseInt(data[0]));
		setAngryCows(Integer.parseInt(data[1]));
		for (int iterator = 2; iterator < data.length; iterator++) {
			corralNumber[iterator - 2] = Integer.parseInt(data[iterator]);
		}
		return corralNumber;

	}

	private List<Integer> filter(Integer[] sortedCorralNumber) {
		List<Integer> filteredList = new ArrayList<Integer>();
		for (Integer i : sortedCorralNumber) {
			filteredList.add(i);
		}

		for (int i = 1; i < filteredList.size(); i++) {
			if (filteredList.get(i) - filteredList.get(i - 1) == 1) {

				filteredList.remove(i);
			}

		}

		return filteredList;

	}

	private List<Integer> getDistanceBetweenAngryCows(List<Integer> filteredList) {
		List<Integer> distanceBetweenCows = new ArrayList<Integer>();
		for (int i = 1; i < filteredList.size(); i++) {
			Integer distanceBetweenClosestCows = filteredList.get(i) - filteredList.get(i - 1);
			distanceBetweenCows.add(distanceBetweenClosestCows);

		}

		return distanceBetweenCows;

	}

	private Integer[] sortAvailableCorrals(Integer[] corralNumber) {

		for (int i = 0; i < corralNumber.length; i++)
			for (int j = 1; j < corralNumber.length - i; j++) {
				if (corralNumber[j - 1] > corralNumber[j]) {
					int trash = corralNumber[j - 1];
					corralNumber[j - 1] = corralNumber[j];
					corralNumber[j] = trash;

				}

			}

		return corralNumber;
	}

	private List sortDistanceBetweenAngryCows(List<Integer> distanceBetweenAngryCowsList) {

		for (int i = 0; i < distanceBetweenAngryCowsList.size(); i++)
			for (int j = 1; j < distanceBetweenAngryCowsList.size() - i; j++) {
				if (distanceBetweenAngryCowsList.get(j - 1) > distanceBetweenAngryCowsList.get(j)) {
					Collections.swap(distanceBetweenAngryCowsList, j - 1, j);
				}

			}

		return distanceBetweenAngryCowsList;
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