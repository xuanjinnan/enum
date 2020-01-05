package examples;
import static examples.Outcome.*;
public enum RoShamBo2 implements Competitor<RoShamBo2>{
	PAPER(DRAW,WIN,LOSE),
	SCISSORS(WIN,DRAW,LOSE),
	ROCK(LOSE,WIN,DRAW)
	;
	RoShamBo2(Outcome paper,Outcome scissors,Outcome rock){
		this.vpaper = paper;
		this.vscissors = scissors;
		this.vrock = rock;
	}
	private Outcome vpaper,vscissors,vrock;
	@Override
	public Outcome compete(RoShamBo2 competitor) {
		switch (competitor) {
		default:
		case PAPER:
			return vpaper;
		case SCISSORS:
			return vscissors;
		case ROCK:
			return vrock;
		}
	}
	public static void main(String[] args) {
		RoShaBo.play(RoShamBo2.class, 20);
	}
}
