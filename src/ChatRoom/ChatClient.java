package ChatRoom;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	
	private static final String URL = "localhost";
	private static final int PORT = 5000;
	
	public void startClient()
	{
		try {
			Socket socket = new Socket(URL, PORT);
			System.out.println("Connected to server.");
			
			// Liên tục nghe xem (đọc dữ liệu) server có gửi gì về không
			ClienttListener client = new ClienttListener(socket);
			new Thread(client).start();
			
			// Liên tục đọc dữ liệu từ Scanner
			OutputStream output = socket.getOutputStream();
			Scanner sc = new Scanner(System.in);
			
			while(true)
			{
				String message = sc.nextLine();
				output.write(message.getBytes());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
