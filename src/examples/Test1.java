package examples;
import static examples.Signal.*;
public class Test1 {
	Signal color = RED;
	public void change() {
		switch(color){
		case RED : color = GREEN;break;
		case GREEN:color = YELLOW;break;
		case YELLOW:	color = RED;break;
		}
	}
	public String toString() {
		return "Test1 is " + color;
	}
	public static void main(String[] args) {
		Test1 test1 = new Test1();
		for(int i = 0; i < 7; i ++) {
			System.out.println(test1);
			test1.change();
		}
	}
	
}
