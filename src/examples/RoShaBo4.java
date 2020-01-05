package examples;
import static examples.Outcome.*;
public enum RoShaBo4 implements Competitor<RoShaBo4>{
	PAPER{
		public Outcome compete(RoShaBo4 com) {
			return compete(ROCK,com);
		}
	},
	SCISSORS{
		public Outcome compete(RoShaBo4 com) {
			return compete(PAPER,com);
		}

	},
	ROCK{
		public Outcome compete(RoShaBo4 com) {
			return compete(SCISSORS,com);
		}
	}
	;
	Outcome compete(RoShaBo4 loser, RoShaBo4 com) {
		return com == this ? DRAW : (com == loser ? WIN : LOSE); 
	}
	public static void main(String[] args) {
		RoShaBo.play(RoShaBo4.class, 20);
	}
}
