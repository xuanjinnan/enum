package practies;

// control/VowelsAndConsonants.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Demonstrates the switch statement
import java.util.*;

public enum VowelsAndConsonants {
	VOWEL('a','e','i','o','u'),SOMETIMES_A_VOWEL('w','y'),CONSONANT('b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','y','z');
	char[] values;
	VowelsAndConsonants(char...cs ){
		values = cs;
	}
	public static  VowelsAndConsonants LetterType(Character c){
		if(Arrays.asList(VOWEL.values).contains(c))
			return VOWEL;
		else if(Arrays.asList(SOMETIMES_A_VOWEL.values).contains(c))
			return SOMETIMES_A_VOWEL;
		return CONSONANT;
	}
	public static void main(String[] args) {
		Random rand = new Random(47);
		for(int i = 0; i < 100; i++) {
			int c = rand.nextInt(26) + 'a';
			System.out.println((char)c + ", " + c + ": " + LetterType((char)c));
			
		}
	}
}
/* Output: (First 13 Lines)
y, 121: Sometimes vowel
n, 110: consonant
z, 122: consonant
b, 98: consonant
r, 114: consonant
n, 110: consonant
y, 121: Sometimes vowel
g, 103: consonant
c, 99: consonant
f, 102: consonant
o, 111: vowel
w, 119: Sometimes vowel
z, 122: consonant
                  ...
 */
