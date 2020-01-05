package examples;
import static examples.Outcome.DRAW;
import static examples.Outcome.LOSE;
import static examples.Outcome.WIN;
public enum RoShaBo3 implements Competitor<RoShaBo3> {
	PAPER{
		public Outcome compete(RoShaBo3 competitor) {
			switch(competitor){
			default:
			case PAPER: return DRAW;
			case SCISSORS: return LOSE;
			case ROCK: return WIN;
			}
		}
	}, 
	SCISSORS {
		public Outcome compete(RoShaBo3 competitor) {
			switch(competitor){
			default:
			case PAPER: return WIN;
			case SCISSORS: return DRAW;
			case ROCK: return LOSE;
			}
		}
	}, 
	ROCK {
		public Outcome compete(RoShaBo3 competitor) {
			switch(competitor){
			default:
			case PAPER: return LOSE;
			case SCISSORS: return WIN;
			case ROCK: return DRAW;
			}
		}
	};
	RoShaBo3() {
	}
	public abstract Outcome compete(RoShaBo3 competitor);
	public static void main(String[] args) {
		RoShaBo.play(RoShaBo3.class, 20);
	}
}
