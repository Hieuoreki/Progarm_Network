package Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class My_Process extends Thread {
	
	private Socket socket;

	public My_Process(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			
			Scanner sc = new Scanner(System.in);
			while(true)
			{
				// Nhận tin nhắn
				String message;
				message=reader.readLine();
			    System.out.println("Client: " + message);
				
				// Gửi tin nhắn
				System.out.println("Server: ");
				message = sc.nextLine();
				writer.println(message);
				writer.flush();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();		}
	}
	
	

}
