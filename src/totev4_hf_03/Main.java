package totev4_hf_03;

import java.io.File;
import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;
//import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class Main {
	
 

	public static void main(String[] args) {
		
		File file = new File("input.txt");

        SimulationDescriptor descriptor = new SimulationDescriptor();
        
        try (Scanner scanner = new Scanner(file)) {
        	if (!scanner.hasNext()) {
        		System.out.println("file is empty");
        		return;
        	}
        	
        	
        	descriptor.productCount = scanner.nextInt();
        	descriptor.assemblyStationCount = scanner.nextInt();
        	descriptor.simulationDuration = scanner.nextInt();
        	scanner.nextLine();
        	
        	for (int i = 0; i < descriptor.productCount; ++i) {
        		String line = scanner.nextLine();
        		Scanner resourceLineScanner = new Scanner(line);
        		String resourceName = resourceLineScanner.next();
        		int initialQuantity = resourceLineScanner.nextInt();
        		Resource resource = new Resource(resourceName, initialQuantity);
        		descriptor.resources.add(resource);
        		resourceLineScanner.close();
        	}
        	
        	for (int i = 0; i < descriptor.assemblyStationCount; ++i) {
        		String inputLine = scanner.nextLine();
        		String outputLine = scanner.nextLine();
        		ArrayList<ConveyorBelt> inputs = new ArrayList<ConveyorBelt>();
        		ArrayList<ConveyorBelt> outputs = new ArrayList<ConveyorBelt>();
        		
        		Scanner resourceLineScanner = new Scanner(inputLine);
        		while (resourceLineScanner.hasNext()) {
        			ConveyorBelt belt = new ConveyorBelt();
        			int resourceIndex = resourceLineScanner.nextInt();
        			belt.capacity = resourceLineScanner.nextInt();
        			belt.resource = descriptor.resources.get(resourceIndex);
        			inputs.add(belt);
        		}
        		resourceLineScanner.close();
        		
        		resourceLineScanner = new Scanner(outputLine);
        		while (resourceLineScanner.hasNext()) {
        			ConveyorBelt belt = new ConveyorBelt();
        			int resourceIndex = resourceLineScanner.nextInt();
        			belt.capacity = resourceLineScanner.nextInt();
        			belt.resource = descriptor.resources.get(resourceIndex);
        			outputs.add(belt);
        		}
        		resourceLineScanner.close();
        		
        		AssemblyStation assemblyStation = new AssemblyStation(inputs, outputs);
        		assemblyStation.helperId = i + 1;
        		descriptor.assemblyStations.add(assemblyStation);
        	}
        	
            
        } catch (IOException ex) {

        } catch (NoSuchElementException ex) {
        	
        }
        
        
        
        System.out.printf("duration: %d\n",descriptor.simulationDuration);
        System.out.printf("product count: %d\n",descriptor.productCount);
        System.out.printf("assembly station count: %d\n",descriptor.assemblyStationCount);

        
	}

}
