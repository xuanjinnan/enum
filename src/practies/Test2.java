package practies;

import java.util.Random;

enum CartoonCharacter {
	SLAPPY,SPANKY,PUNCHY,SILLY,BOUNCY,NUTTY,BOB;
	private static Random ran = new Random(47);
	public static CartoonCharacter next() {
		return values()[ran.nextInt(values().length)];
	}
}
	
public class Test2 {
	public static void printNext(CartoonCharacter cc) {
		System.out.println(CartoonCharacter.next() + ". ");
	}
	public static void main(String[] args) {
		//Choose any instance:
		CartoonCharacter cc = CartoonCharacter.BOB;
		for(int i = 0; i< 10; i++)
			printNext(cc);
	}
}
