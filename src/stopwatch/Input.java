package stopwatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input extends Thread {
	Stopwatch stopwatch = Stopwatch.getInstance();
	
	private static Input instance = new Input();
	
	public static Input getInstance() {
		return instance;		
	}
	
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	@Override
	public void run() {
		while(true) {
			try {
				String input = reader.readLine();
				
				if(input.equals("q")) {
					stopwatch.interrupt();	
				} else if(input.equals("w")) {
					stopwatch.isRun = false;					
				} else if(input.equals("e")) {
					stopwatch.reRun();								
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	

}
