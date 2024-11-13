package stopwatch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Stopwatch extends Thread {

	private static Stopwatch instance = new Stopwatch();

	public static Stopwatch getInstance() {
		return instance;
	}

	private StringBuffer buffer = new StringBuffer();
	private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	@Override
	public void run() {
		while (true) {
			Calendar cal = Calendar.getInstance();
			buffer.append(sdf.format(cal.getTime()));
			try {
				writer.append(buffer);
				writer.flush();
				buffer.delete(0, buffer.length());
				writer.append("\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			sleep();
		}
	}

	private void sleep() {
		try {
			Thread.sleep(900);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
