package algorithm;

public class Book implements Comparable <Book> {
	private int pages;
	private String author;
	private double price;
	

	@Override
	public String toString() {
		return this.author + " " + this.pages + " " + this.price;
	}

	public Book(String author, int pages, double price) {
		this.author = author;
		this.pages = pages;
		this.price = price;

	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	

	public int comparePriceTo(Book b) {
		 if (this.price > b.getPrice())
	            return 1;
	        if (this.price < b.getPrice())
	            return -1;
	        if (this.price == b.getPrice())
	            return 0;
	        return 0;
		
	}

	@Override
	public int compareTo(Book o) {
		// TODO Auto-generated method stub
		return 0;
	}


}
