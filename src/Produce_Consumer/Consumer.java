package Produce_Consumer;

public class Consumer extends Thread {
	
	private int ID;
	private Buffer buffer;
	
	protected Consumer(int ID, Buffer buffer) {
		this.ID = ID;
		this.buffer = buffer;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			// Chỉ cần xác định có còn sản phẩm trong kho hay không thôi
			if(buffer.getSize() > 0)
			{
				try {
					buffer.removeProduct(this.ID);
					sleep(500); // cho dây chuyền nghỉ 0.5s
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}
}
