package InetAddress_Url;

import java.net.InetAddress;

public class InetAdress {
	
	public static void main(String[] args) {
		try {
			// Ví dụ 1
			String domain = "www.google.com";
			InetAddress address = InetAddress.getByName(domain);
			System.out.println("Tên miền: " + domain);
			System.out.println("IP: " + address.getHostAddress());
			
			// Ví dụ 2
			InetAddress localhost = InetAddress.getLocalHost();
			System.out.println("Địa chỉ của local host: " + localhost.getHostAddress());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
