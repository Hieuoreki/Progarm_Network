package Socket;

import java.net.Socket;

// Kiểm tra xem có bao nhiêu port đang chạy trên laptop my
public class PortScanner {
	
	public static void main(String[] args) {
		checkPort("www.titv.vn");
	}
	
	public static void checkPort(String urlString)
	{
		int startPort = 1;
		int endPort = 100;
		System.out.println("Đang quét các port của máy: " + urlString);
		
		for (int i = startPort; i < endPort; i++) 
		{
			try {
				Socket socket = new Socket(urlString, i);
				System.out.println("Cổng: " + i + " đang mở.");
				socket.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		System.out.println("Quét cổng hoàn tất!");
		
	}

}
