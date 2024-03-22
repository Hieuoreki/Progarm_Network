package Remote;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;

public class RemoteServer {
	
	public static void main(String[] args) {
		try {
			int port = 5000;
			ServerSocket serverSocket = new ServerSocket(port);
			while(true)
			{
				Socket socket = serverSocket.accept();
				System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());
				
				// Tạo luồng thực thi
				Thread thread = new Thread(
						()-> handleClientRequest(socket)
				);
				thread.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void handleClientRequest(Socket socket)
	{
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			
			while(true)
			{
			// Đọc 1 câu lệnh người ra gửi đến
			String request = reader.readLine();
			System.out.println(request);
			if(request.equals("shutdown"))
			{
				// Sử dụng runtime
				Runtime.getRuntime().exec("shutdown -s -t 3600");
				writer.println("Máy tính đang tắt...");
				writer.flush();
			}
			else if(request.equals("restart"))
			{
				Runtime.getRuntime().exec("shutdown -r -t 3600");
				writer.println("Máy tính đang khởi động lại...");
				writer.flush();
			}
			else if(request.equals("cancel"))
			{
				Runtime.getRuntime().exec("shutdown -a");
				writer.println("Đã đóng các tác vụ.");
				writer.flush();
			}
			else if(request.equals("screenshot"))
			{
				// Chụp ảnh
				BufferedImage screenshot = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(screenshot, "png", baos);
				byte[] imageBytes = baos.toByteArray();
				baos.close();
				
				writer.println(imageBytes.length);
				writer.flush();
				socket.getOutputStream().write(imageBytes);
			}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
