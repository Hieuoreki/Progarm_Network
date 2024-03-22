package ChatRoom;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	
	private static final int PORT = 5000;
	private List<ClientHandler> clients = new ArrayList<ClientHandler>();
	
	public void startServer()
	{
		try {
			// Websocket
			ServerSocket serverSocket = new ServerSocket(PORT);
			System.out.println("Server started. Listening on port: " + PORT);
			
			// Client connects to server
			while(true)
			{
				Socket clientSocket = serverSocket.accept();
				System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());
				
				ClientHandler clientHandler = new ClientHandler(clientSocket, System.currentTimeMillis() + "", this);
				clients.add(clientHandler);
				new Thread(clientHandler).start();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void broadcastMessage(String id, String message)
	{
		for(ClientHandler client : clients)
		{
			if(!(client.getId().equals(id)))
			client.sendMessage(id + " : " + message);
		}
	}

}
