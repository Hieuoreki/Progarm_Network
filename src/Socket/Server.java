package Socket;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) {
		
		try {
			// Tạo server Socket và lắng nghe
			int port = 99;
			ServerSocket serverSocket = new ServerSocket(port);
			
			// Chấp nhận nhiều kết nối
			while(true)
			{
				// Chấp nhận kết nối từ 1 Client
			    Socket clientSocket = serverSocket.accept();
			    My_Process mp = new My_Process(clientSocket);
			    mp.start();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
