package Produce_Consumer;

import java.util.ArrayList;

public class Buffer {
	// Khả năng chưá tối đa
	private int capacity;
	// Danh sách sản phẩm
	private ArrayList<Integer> products = new ArrayList<>();
	
	
	
	protected Buffer(int capacity) {
		this.capacity = capacity;
		products = new ArrayList<Integer>();
	}

	// Hàm để nhà sản xuất bỏ sản phẩm vào
	public void addProduct(int product, int productID)
	{
		System.out.println("-------------------------------------------------");
		System.out.println("Nhà sản xuất: " + productID + " đã thêm sản phẩm " + product);
		products.add(product);
		System.out.println("Số lượng tồn kho: " + products.size());
	}
	
	// Hàm để user mua hàng
	public void removeProduct(int consumerID)
	{
		System.out.println("-------------------------------------------------");
		System.out.println("Khách hàng: " + consumerID + " đã mua sản phẩm " + products.get(0));
		products.remove(0);
		System.out.println("Số lượng tồn kho: " + products.size());
	}
	
	public int getSize()
	{
		return this.products.size();
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
}
