package stopwatch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Stopwatch extends Thread {

	private static Stopwatch instance = new Stopwatch();

	public boolean isRun = true;
	private String message;
	private int count;

	public static Stopwatch getInstance() {
		return instance;
	}

	private StringBuffer buffer = new StringBuffer();
	private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	@Override
	public void run() {		
		Input input = Input.getInstance();
		input.start();	
		
		printMenu();
		while (isRun) {
			try {
				play();
				Thread.sleep(1000);
			} catch (InterruptedException e) {				
				printResult();
				break;
			}
		}	
	}

	private void printMenu() {
		System.out.println("[q] STOP");
		System.out.println("[w] HOLD");
		System.out.println("[e] RERUN");
	}

	private void play() {
		Calendar cal = Calendar.getInstance();
		buffer.append(sdf.format(cal.getTime()));
		message = String.format(" [%d sec]\n", ++count);
		buffer.append(message);
		try {
			writer.append(buffer);
			writer.flush();
			buffer.delete(0, buffer.length());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printResult() {
		int minute = count / 60;
		int result = count - (minute * 60);
		String info = String.format(">>> %d분 %d초 소요됨", minute, result);
		buffer.append(info);
		try {
			writer.append(buffer);
			writer.flush();
		} catch (Exception e) {
		}
	}

	public void reRun() {
		isRun = true;
	}


}
