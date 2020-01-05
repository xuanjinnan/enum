package examples;
import static examples.Outcome.*;
public enum RoShaBo6 implements Competitor<RoShaBo6> {
	PAPER,SCISSORS,ROCK
	;
	private Outcome[][] table = {
			{DRAW,LOSE,WIN},
			{WIN,DRAW,LOSE},
			{LOSE,WIN,DRAW},
	};

	public Outcome compete(RoShaBo6 competitor) {
		return table[ordinal()][competitor.ordinal()];
	}
	public static void main(String[] args) {
		RoShaBo.play(RoShaBo6.class, 20);
	}
}
