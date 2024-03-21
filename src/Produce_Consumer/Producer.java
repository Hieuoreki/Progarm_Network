package Produce_Consumer;

import java.util.Random;

public class Producer extends Thread {
	
	private int ID;
	private Buffer buffer;
	
	protected Producer(int ID, Buffer buffer) {
		this.ID = ID;
		this.buffer = buffer;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = 0;
		while(true)
		{
			// xác định nếu kho còn chỗ trống tức là kho k bị quá tải
			if(buffer.getCapacity() > buffer.getSize())
			{
				try {
					buffer.addProduct(i++, ID);
					sleep((long)Math.random()*100); // cho dây chuyền nghỉ 0.5s
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}
	
	
}
