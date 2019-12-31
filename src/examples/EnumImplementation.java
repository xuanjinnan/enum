package examples;

import java.util.Random;

enum CartoonCharacter implements Generator<CartoonCharacter>	{
	SLAPPY,SPANKY,PUNCHY,SILLY,BOUNCY,NUTTY,BOB;
	Random ran = new Random(47);
	@Override
	public CartoonCharacter next() {
		return values()[ran.nextInt(values().length)];
	}
}
public class EnumImplementation {
	public static <T> void printNext(Generator<T> rg) {
		System.out.println(rg.next() + ". ");
	}
	public static void main(String[] args) {
		//Choose any instance:
		CartoonCharacter cc = CartoonCharacter.BOB;
		for(int i = 0; i< 10; i++)
			printNext(cc);
	}
}
