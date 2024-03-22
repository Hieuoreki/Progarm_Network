package ChatRoom;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
	
	private Socket mySocket;
	private ChatServer chatServer;
	private String id;
	private InputStream input;
	private OutputStream output;
	
	protected ClientHandler(Socket mySocket, String id, ChatServer chatServer) {
		this.mySocket = mySocket;
		this.chatServer = chatServer;
		this.id = id;
		
		try {
			this.input = mySocket.getInputStream();
			this.output = mySocket.getOutputStream();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			byte[] buffer = new byte[1024];
			int bytesRead;
			while((bytesRead = input.read(buffer)) != -1)
			{
				String message = new String(buffer, 0, bytesRead);
				chatServer.broadcastMessage(this.id, message);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void sendMessage(String message)
	{
		try {
			output.write(message.getBytes());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	

}
