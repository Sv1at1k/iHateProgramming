package algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;



public class SortManager {
	private List<Book> books = new ArrayList<Book>();

	public List<Book> getBooksFromList(String file) {
		try (FileReader fileReader = new FileReader(file)) {
			Scanner scan = new Scanner(fileReader);
			while (scan.hasNextLine()) {

				String readedString = scan.nextLine();
				String[] bookInfo = readedString.split(",");

				String author = bookInfo[0];
				int pages = Integer.parseInt(bookInfo[1]);
				double price = Double.parseDouble(bookInfo[2]);

				books.add(new Book(author, pages, price));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("FileNotFound");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return books;

	}

	public List<Book> insertSortByPagesDec() {

		long workTime = 0;
		int comparisonOperations = 0;
		int exchangeOperations = 0;
		System.out.println("!!!Insertion Sort!!!!");
		Book[] booksArray = new Book[books.size()];
		booksArray = books.toArray(booksArray);

		 long startTime = System.nanoTime();
		for (int i = 1; i < booksArray.length; i++) {
			Book key = booksArray[i];
			int j = i - 1;

			while (j >= 0 && booksArray[j].getPages() < key.getPages()) {
				booksArray[j + 1] = booksArray[j];
				j = j - 1;
				comparisonOperations += 1;
				

			}
			booksArray[j + 1] = key;
			exchangeOperations += 1;

		}



		workTime = System.nanoTime() - startTime;

		System.out.println("Number of exchanges:" + exchangeOperations);
		System.out.println("Number of comparison operations:" + comparisonOperations);
		System.out.println("Work time:" + workTime + " nanoseconds");
		System.out.println("Result:");

		books = Arrays.asList(booksArray);
		for (Book book : books ) {
			System.out.println(book.toString());
			
		}
		return  books;
	}
	public void mergeSortPageDecr() {
		class PriceComparator implements Comparator<Book> {
	        public int compare(Book a, Book b) {
	            return a.comparePriceTo(b);
	        }
	    }
		ArrayList<Book> arr = new  ArrayList<Book>();
		arr.addAll(this.books);
		MergeSort.mergeSort(arr, new PriceComparator());
		for (Book book : arr ) {
			System.out.println(book.toString());
			
			}

		
		
		
				
		
		
	}
  
	
	
	
	
	
	public void addBook(Book book) {

		this.books.add(book);
	}

	public List<Book> getBooks() {


		
		return this.books;
	}

	public void printBooksList() {
		for (int i = 0; i < books.size(); i++) {
			System.out.println(books.get(i).toString());

		}

	}

}