package Produce_Consumer;

public class Main {
	
	public static void main(String[] args) {
		Buffer buffer = new Buffer(5);
		Producer p1 = new Producer(333, buffer);
		Consumer c1 = new Consumer(123, buffer);
		
		// Bắt đầu mua hàng
		c1.start();
		// bắt đầu sản xuất
		p1.start();
	}

}
