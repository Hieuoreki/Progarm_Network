package ChatRoom;

import java.io.InputStream;
import java.net.Socket;

public class ClienttListener implements Runnable {
	
	private Socket socket;
	private InputStream input;
	
	

	protected ClienttListener(Socket socket) {
		this.socket = socket;
		
		try {
			this.input = socket.getInputStream();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
				System.out.println(message);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	

}
