package Synchronixed;

public class Counter {
	
	private int count = 0;
	
	// synchronized: đồng bộ, tại 1 thời điểm chỉ có 1 Thread gọi
	public synchronized void increment()
	{
		count++;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	

}
