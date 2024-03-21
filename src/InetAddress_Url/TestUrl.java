package InetAddress_Url;

import java.net.HttpURLConnection;
import java.net.URL;

public class TestUrl {
	
	public static void main(String[] args) {
		String[] websites = 
			{
					"https://titv.vn",
					"https://google.com",
					"https://vnexpress1.net"
			};
		
		for (String website : websites) {
			checkWebsite(website);
			
		}
		
	}
	
	public static void checkWebsite(String urlString)
	{
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// http code: 200(thành công), 401(không có quyền truy cập), 403(không có quyền truy xuất), 500(lỗi server), 404(trang không tìm thấy)
			int reponsecode = conn.getResponseCode();
			
			if(reponsecode == 200)
			{
				System.out.println(urlString + " trang web hoạt động!");
			}
			else
			{
				System.out.println(urlString + " trang web không hoạt động! Mã code:  " + reponsecode);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(urlString + " Không thể kết nối!");
		}
	}

}
