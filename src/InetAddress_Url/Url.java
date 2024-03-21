package InetAddress_Url;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Url {
	
	public static void main(String[] args) {
		try {
			// Tạo đối tượng URL
			String urlString = "https://vi.wikipedia.org/wiki/Vi%E1%BB%87t_Nam";
			URL url = new URL(urlString);
			
			// Đọc dữ liệu từ trang web đó
			InputStreamReader isr = new InputStreamReader(url.openStream());
			BufferedReader br = new BufferedReader(isr);
			String line;
			while((line=br.readLine())!= null)
			{
				System.out.println(line);
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
