package Library_Manage;

public class Main {
	
	public static void main(String[] args) {
		
		Library lib1 = new Library();
		
		lib1.addBook(new Book("Java Best", "Oreki"));
		lib1.addBook(new Book("C++ Best", "Hieu"));
		lib1.addBook(new Book("CTDL&GT Best", "Tien"));
		lib1.addBook(new Book("C# Best", "Kinh"));
		
		// In ra
		lib1.displayBooks();
		
		// Mượn và trả sách
//		lib1.borrowBook("Java Best");
//		lib1.borrowBook("Java Best");
//		lib1.returnBook("Java Best");
//		lib1.borrowBook("Java Best");
		
		Thread th1 = new Student(1, lib1, "Java Best");
		Thread th2 = new Student(2, lib1, "Java Best");
		Thread th3 = new Student(3, lib1, "C++ Best");
		Thread th4 = new Student(4, lib1, "C++ Best");
		
		th1.start();
		th2.start();
		th3.start();
		th4.start();
		
	}

}
