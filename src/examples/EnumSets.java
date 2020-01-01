package examples;

import static examples.AlarmPoints.BATHROOM;
import static examples.AlarmPoints.KITCHEN;
import static examples.AlarmPoints.STAIR1;
import static examples.AlarmPoints.STAIR2;

import java.util.EnumSet;
public class EnumSets {
	public static void main(String[] args) throws Exception, SecurityException {
		EnumSet<AlarmPoints> points = EnumSet.noneOf(AlarmPoints.class);
		points.add(BATHROOM);
		System.out.println("add BATHROOM " +points);
		points.addAll(EnumSet.of(STAIR1, STAIR2, KITCHEN));
		System.out.println("add three elements:" + points);
		points = EnumSet.allOf(AlarmPoints.class);
		System.out.println("allOf:" + points);
		points.removeAll(EnumSet.of(STAIR1, STAIR2, KITCHEN));
		System.out.println("after romove three elements: " + points);
		points = EnumSet.complementOf(points);
		System.out.println("after complementsOf points:" + points);
	}

}
