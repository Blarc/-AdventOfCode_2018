import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day4_puzzle1 {
	
	public static class Event {
		int month;
		int day;
		int hour;
		int minute;
		String[] msg;
		
		Event next;
		
		public Event (int month, int day, int hour, int minute, String[] msg) {
			this.month = month;
			this.day = day;
			this.hour = hour;
			this.minute = minute;
			this.msg = msg;
		}
		
		public Event (int month, int day, int hour, int minute, String[] msg, Event next) {
			this.month = month;
			this.day = day;
			this.hour = hour;
			this.minute = minute;
			this.msg = msg;
			this.next = next;
		}
		
		public boolean isLessThan(Event a) {
			if (month < a.month) {
				return true;
			}
			else if (month > a.month) {
				return false;
			}
			else {
				if (day < a.day) {
					return true;
				}
				else if (day > a.day) {
					return false;
				}
				else {
					if (hour < a.hour) {
						return true;
					}
					else if (hour > a.hour) {
						return false;
					}
					else {
						if (minute < a.minute) {
							return true;
						}
						else if (minute > a.minute) {
							return false;
						}
					}
				}
			}
			return false;
		}
	}
	

	public static void main(String[] args) throws IOException {

		if (args.length < 1) {
			System.out.println("manjka vhod");
			System.exit(1);
		}
		
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		
		Event[] events = new Event[1128];
		
		Pattern p = Pattern.compile("\\d+");
		Matcher m;
		
		Event first = new Event(0, 0, 0, 0, "0 0".split(" "));
		String readLine;
		for (int i = 0; (readLine = br.readLine()) != null; i++) {
			System.out.println(i);
			List<String> allMatches = new ArrayList<String>();
			m = p.matcher(readLine);
			while (m.find()) {
				allMatches.add(m.group());
			}
			
			int month = Integer.parseInt(allMatches.get(1));
			int day = Integer.parseInt(allMatches.get(2));
			int hour = Integer.parseInt(allMatches.get(3));
			int minute = Integer.parseInt(allMatches.get(4));
			
			Event newEvent = new Event(month, day, hour, minute, readLine.split(" "));
			
			Event temp = first;
			
			while (temp.next != null) {
				if (temp.next.isLessThan(newEvent)) {
					temp = temp.next;
				}
			}
		}
	}
}
