package examples;

import java.util.EnumMap;
import static examples.Outcome.*;
public enum RoShaBo5 implements Competitor<RoShaBo5>{
	PAPER,SCISSORS,ROCK;
	static EnumMap<RoShaBo5,EnumMap<RoShaBo5,Outcome>> table = new EnumMap<>(RoShaBo5.class);
	static{
		for(RoShaBo5 ro : values()){
			table.put(ro, new EnumMap<RoShaBo5,Outcome>(RoShaBo5.class));
		}
		initRow(PAPER,DRAW,LOSE,WIN);
		initRow(SCISSORS,WIN,DRAW,LOSE);
		initRow(ROCK,LOSE,WIN,DRAW);
	}
	private static void initRow(RoShaBo5 ro, Outcome vpaper, Outcome vscissors, Outcome vrock) {
		table.get(ro).put(PAPER, vpaper);
		table.get(ro).put(SCISSORS, vscissors);
		table.get(ro).put(ROCK, vrock);
	}
	public Outcome compete(RoShaBo5 competitor) {
		return table.get(this).get(competitor);
	}
	public static void main(String[] args) {
		RoShaBo.play(RoShaBo5.class, 20);
	}
}
