package Library_Manage;

public class Student extends Thread {
	
	private Library lib;
	private long ID;
	private String title;

	protected Student(long ID, Library lib, String title) {
		this.ID = ID;
		this.lib = lib;
		this.title = title;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			for(int i=0; i<5; i++) {
				System.out.println(ID);
				lib.borrowBook(ID, title);
				sleep((long)(Math.random()*1000));
				System.out.println(ID);
				lib.returnBook(ID, title);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	

}
