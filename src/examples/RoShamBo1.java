package examples;
import static examples.Outcome.*;

import java.util.Random;
enum Outcome{WIN,LOSE,DRAW}
interface Item{
	Outcome compete(Item it);
	Outcome eval(Rock rock);
	Outcome eval(Scissors sissors);
	Outcome eval(Paper p);
}
class Rock implements Item{
	@Override
	public Outcome compete(Item it) {
		return it.eval(this);
	}

	public Outcome eval(Rock rock) {
		return DRAW;
	}

	public Outcome eval(Scissors sissors) {
		return LOSE;
	}

	public Outcome eval(Paper p) {
		return WIN;
	}
	public String toString(){return "Rock";}
}
class Scissors implements Item{
	public Outcome compete(Item it) {
		return it.eval(this);
	}
	public Outcome eval(Rock rock) {
		return WIN;
	}
	public Outcome eval(Scissors sissors) {
		return DRAW;
	}
	public Outcome eval(Paper p) {
		return LOSE;
	}
	public String toString(){return "Scissors";}
}
class Paper implements Item{
	public Outcome compete(Item it) {
		return it.eval(this);
	}
	public Outcome eval(Rock rock) {
		return LOSE;
	}
	public Outcome eval(Scissors sissors) {
		return WIN;
	}
	public Outcome eval(Paper p) {
		return DRAW;
	}
	public String toString(){
		return "Paper";
	}
}
public class RoShamBo1 {
	static final int SIZE = 20;
	static Random ran = new Random(47);
	public static Item newItem(){
		switch(ran.nextInt(3)){
		default:
		case 0: return new Scissors();
		case 1: return new Paper();
		case 2: return new Rock();
		}
	}
	public static String match(Item a,Item b){
		return a + " :vs " + b + ": " + a.compete(b); 
	}
	public RoShamBo1() {
	}
	public static void main(String[] args) {
		for(int i = 0; i < SIZE; i++){
			System.out.println(match(newItem(),newItem()));
		}
	}

}
