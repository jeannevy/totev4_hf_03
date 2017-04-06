package totev4_hf_03;

public class Resource {
	private String name;
	private Integer quantity;
	
	public Resource(String name, int initialQuantity) {
		this.name = name;
		this.quantity = initialQuantity;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	public Boolean increaseQuantity(int withQuantity, Object sender) {
		synchronized (this.quantity) {
			this.quantity += withQuantity;
			
	        System.out.printf("Resource named: %s increased with:%d by thread:%d\n",this.name,withQuantity, Thread.currentThread().getName());
	        System.out.printf("quantity %d\n",this.quantity);

	        return true;
		}
	}
	public Boolean decreaseQuantity(int withQuantity, Object sender) {
		if (withQuantity > this.quantity) {
			return false;
		}
		synchronized (this.quantity) {
			this.quantity += withQuantity;
			
	        System.out.printf("Resource named: %s decreased with:%d by thread:%d\n",this.name,withQuantity, Thread.currentThread().getName());
	        System.out.printf("quantity %d\n",this.quantity);

	        return true;
		}
	}
}

