import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LeafManager {
	private String fsile;
	private int width;
	private int height;
	private int leafsAmount;

	public void calculate() {
		setData(readFromFile());
		int probablyAnswer = (int) Math.ceil(Math.sqrt((width * height * leafsAmount)));
		int maxLeafsWidth = probablyAnswer / width;
		int maxLeafsHeight = probablyAnswer / height;
		while (maxLeafsWidth * maxLeafsHeight < leafsAmount) {
			int ñubesToFitWidth = width - probablyAnswer % width;
			int ñubesToFitHeight = height - probablyAnswer % height;
			if (ñubesToFitWidth < ñubesToFitHeight) {
				probablyAnswer = probablyAnswer + ñubesToFitWidth;
				maxLeafsWidth = probablyAnswer / width;
				maxLeafsHeight = probablyAnswer / height;

			} else if (ñubesToFitHeight < ñubesToFitWidth) {
				probablyAnswer = probablyAnswer + ñubesToFitHeight;
				maxLeafsWidth = probablyAnswer / width;
				maxLeafsHeight = probablyAnswer / height;

			} else {
				probablyAnswer = probablyAnswer + 1;
				maxLeafsWidth = probablyAnswer / width;
				maxLeafsHeight = probablyAnswer / height;

			}
		}
		System.out.println("Answer is " + probablyAnswer);
	}

	private void setData(String readedString) {
		String[] data = readedString.split(" ");
		this.leafsAmount = Integer.parseInt(data[0]);
		this.width = Integer.parseInt(data[1]);
		this.height = Integer.parseInt(data[2]);
	}

	private String readFromFile() {
		String readedString = new String("");
		try (FileReader fileReader = new FileReader(fsile)) {
			Scanner scan = new Scanner(fileReader);

			while (scan.hasNextLine()) {
				readedString += scan.nextLine();

			}

		} catch (FileNotFoundException e) {
			System.out.print("FIle Not Found");
		} catch (IOException e) {
			System.out.print("Something with output");

		}
		return readedString;

	}

	public LeafManager(String file) {

		this.fsile = file;
	}

}