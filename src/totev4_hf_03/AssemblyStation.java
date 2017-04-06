package totev4_hf_03;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class AssemblyStation implements Runnable {
	
	public int helperId;
	private ArrayList<ConveyorBelt> inputs;
	private ArrayList<ConveyorBelt> outputs;

	public AssemblyStation(ArrayList<ConveyorBelt> inputs, ArrayList<ConveyorBelt> outputs) {
		this.inputs = inputs;
		this.outputs = outputs;
	}
	
	private boolean canProduce() {
		for (ConveyorBelt conveyorBelt : this.inputs) {
			if (conveyorBelt.resource.getQuantity() < conveyorBelt.capacity) {
				return false;
			}
		}
		return true;
	}
	
	private boolean decreaseResources() {
		ArrayList<Future<Boolean>> futures = new ArrayList<Future<Boolean>>();
		for (ConveyorBelt conveyorBelt : this.inputs) {
			Callable<Boolean> task = () -> {
				return conveyorBelt.resource.decreaseQuantity(conveyorBelt.capacity, task);
			};
			ExecutorService executor = Executors.newFixedThreadPool(1);
			Future<Boolean> future = executor.submit(task);
			futures.add(future);
		}
	}

	public void run() {
	
		
		

	}

}
