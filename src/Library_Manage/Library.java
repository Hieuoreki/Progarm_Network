package Library_Manage;

import java.util.ArrayList;
import java.util.List;

public class Library {
	
	private List<Book> books;
	
	public Library() {
		books = new ArrayList<>();
	}
	
	public synchronized void addBook(Book book)
	{
		books.add(book);
		System.out.println("Tôi đã thêm sách: " + book.getTitle());
	}
	
	public synchronized void borrowBook(long ID, String title)
	{
		for(Book book : books)
		{
			if(book.getTitle().equals(title))
			{
				if(book.isAvailable())
				{
					book.setAvailable(false);
					System.out.println(ID + " : " + " Đã cho mượn sách: " + book.getTitle());
					return;
				}
			}
		}
		System.out.println("Không thể cho mượn sách!");
	}
	
	public synchronized void returnBook(long ID, String title)
	{
		for(Book book : books)
		{
			if(book.getTitle().equals(title))
			{
				if(!book.isAvailable())
				{
					book.setAvailable(true);
					System.out.println(ID + " : " + "Đã trả sách: " + book.getTitle());
					return;
				}
			}
		}
		System.out.println("Không thể trả sách!");
	}
	
	public synchronized void displayBooks()
	{
		System.out.println("Library books: ");
		for(Book book : books)
		{
			System.out.println(book.toString());
		}
	}

}
