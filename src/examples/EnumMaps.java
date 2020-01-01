package examples;

import java.util.EnumMap;
import java.util.Map;

import static examples.AlarmPoints.*;
interface Command{void action();}
public class EnumMaps {
	public static void main(String[] args) {
		EnumMap<AlarmPoints,Command> em = 
				new EnumMap<AlarmPoints,Command>(AlarmPoints.class);
		em.put(KITCHEN, new Command() {

			@Override
			public void action() {
				System.out.println("Kitchen fire!");
			}
		});
		em.put(BATHROOM,new Command() {

			@Override
			public void action() {
				System.out.println("Bathroom alert!");
			}

		});
		em.put(BATHROOM,new Command() {

			@Override
			public void action() {
				System.out.println("Bathroom override!");
			}

		});
		for(Map.Entry<AlarmPoints, Command> e : em.entrySet()) {
			System.out.println(e.getKey() + ":" );
			e.getValue().action();
		}
		//if there's no value for a particular key
		try {
			em.get(UTILITY).action();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
