package algorithm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class main {

public static void main(String[] args) {
	SortManager sortManager = new SortManager();
	sortManager.getBooksFromList("E:\\Programing\\java\\labs\\Algorithm 1\\src\\books.txt");
	sortManager.printBooksList();
	sortManager.insertSortByPagesDec();
	sortManager.mergeSortPageDecr(); 



}
}
