package practies;


import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;

import static examples.Input.*;

import examples.Generator;
import  examples.Input;
import net.mindview.util.TextFile;
enum Category{
	MONEY(NICKEL,DIME,QUARTER,DOLLAR),
	ITEM_SELECTION(TOOTHPASTE,CHIPS,SODA,SOAP),
	QUIT_TRANSACTION(ABORT_TRANSACTION),
	SHUT_DOWN(STOP);
	private Input[] values;
	Category(Input... values){
		this.values = values;
	}
	private static EnumMap<Input,Category> categories = new EnumMap<Input,Category>(Input.class);
	static {
		for(Category c :Category.class.getEnumConstants())
			for(Input type : c.values)
				categories.put(type, c);
	}
	public static Category categorize(Input input) {
		return categories.get(input);
	}
}
interface Command{	// In order to use a Command Design Pattern
	void next(Input input);
	void next();
}

enum State{
	RESTING,
	ADDING_MONEY,
	DISPENSING,
	GIVING_CHANGE,
	TERMINAL
}
public class VendingMachine10 {
	static int count = 0;
	int id = ++count;
	State state = State.RESTING;
	int amount = 0; // for each transaction
	int banked = 0; // retained after transactions
	Input input = null;
	Input selection = null;
	boolean isTransient = false;
	//Enums must be static,sot use classes instead:
	class RestingDo implements Command{
		public void next(Input in) {
			isTransient = false;
			input = in;
			switch(Category.categorize(in)) {
			case MONEY:
				amount += in.amount();
				state = State.ADDING_MONEY;
				break;
			case SHUT_DOWN:
				state = State.TERMINAL;
			default:
			}
		}
		public void next() {
			isTransient = false;
		}
	}
	class AddingMoneyDo implements Command{
		public void next(Input input) {
			isTransient = false;
			switch(Category.categorize(input)) {
			case MONEY:
				amount += input.amount();
				break;
			case ITEM_SELECTION:
				selection = input;
				if(amount < selection.amount()) 
					System.out.println("Insufficient money for " + selection);
				else {
					state = State.DISPENSING;
					isTransient = true;
				}
				break;
			case QUIT_TRANSACTION:
				state = State.GIVING_CHANGE;
				isTransient = true;
				break;
			case SHUT_DOWN:
				state = State.TERMINAL;
				banked = banked += amount;
			default:
			}
		}
		public void next() {
			isTransient = false;
		}
	}
	class DispensingDo implements Command{
		public void next(Input input) {
			isTransient = true;
			System.out.println("Here is your " + selection);
			state = State.GIVING_CHANGE;
		}
		public void next() {
			isTransient = true;
			System.out.println("Here is your " + selection);
			state = State.GIVING_CHANGE;
		}
	}
	class GivingChangDo implements Command{
		public void next(Input input) {
			isTransient = true;
			if(amount > selection.amount())
				System.out.println("Your change: " + (amount - selection.amount()));
			banked = banked += selection.amount();
			amount = 0; //reset
			state = State.RESTING;
		}
		public void next() {
			isTransient = true;
			if(amount < selection.amount())
				System.out.println("Returning your: " + amount);
			if(amount > selection.amount()) {
				System.out.println("Your change: " + (amount - selection.amount()));
				banked = banked += selection.amount();
			}
			if(amount == selection.amount())
				banked = banked += selection.amount();
			amount = 0;
			state = State.RESTING;
		}
		
	}
	class TerminalDo implements Command{
		public void next(Input input) {
			System.out.println("state RERMINAL");
			isTransient = false;
		}
		public void next() {
			System.out.println("state TERMINAL");
			isTransient = false;
		}
	}
	Map<State,Command> em = Collections.synchronizedMap(new EnumMap<State,Command>(State.class));
	VendingMachine10(){ // Load up the EnumMap in the constructor
		System.out.println("Vendingmachine10()#" + id);
		em.put(State.RESTING, new RestingDo());
		em.put(State.ADDING_MONEY, new AddingMoneyDo());
		em.put(State.DISPENSING, new DispensingDo());
		em.put(State.GIVING_CHANGE, new GivingChangDo());
		em.put(State.TERMINAL, new TerminalDo());
	}
	void showAmount() {
		System.out.println("amount = " + amount);
	}
	void showBanked() {
		System.out.println("banked = " + banked);
	}
	public static void main(String[] args) {
		args = new String[] {"E:\\学习\\thinkInJava_at_git\\enum\\src\\practies\\VendingMachine.txt"};
		Generator<Input> gen = new RandomInputGenerator();
		if(args.length ==1)
			gen = new FileInputGenerator10(args[0]);
		VendingMachine10 vm10a = new VendingMachine10();
		VendingMachine10 vm10b = new VendingMachine10();
		VendingMachine10 vm10c = new VendingMachine10();
		System.out.println();
		System.out.println("Testing VendingMachine 10a:");
		while(vm10a.state != State.TERMINAL) {
			Input in = gen.next();
			vm10a.em.get(vm10a.state).next(in);
			while(vm10a.isTransient)
				vm10a.em.get(vm10a.state).next();
			vm10a.showAmount();
		}
		vm10a.showBanked();
		System.out.println();
		System.out.println("Testing VendingMachine 10b: ");
		gen = new FileInputGenerator10("E:\\学习\\thinkInJava_at_git\\enum\\src\\practies\\VendingMachine.txt");
		while(vm10b.state != State.TERMINAL) {
			Input in = gen.next();
			vm10b.em.get(vm10b.state).next(in);
			while(vm10b.isTransient) {
				vm10b.em.get(vm10b.state).next();
			}
			vm10b.showAmount();
		}
		System.out.println();
		System.out.println("Testing VendingMachine 10c:");
		gen = new FileInputGenerator10("E:\\学习\\thinkInJava_at_git\\enum\\src\\practies\\VendingMachine.txt");
		while(vm10c.state != State.TERMINAL) {
			Input in = gen.next();
			vm10c.em.get(vm10c.state).next(in);
			while(vm10c.isTransient) {
				vm10c.em.get(vm10c.state).next();
			}
			vm10c.showAmount();
		}
	}
}
//For a basic sanity check
class RandomInputGenerator implements Generator<Input>{
	public Input next() {
		return Input.randomSelection();
	}
}
//Create Inputs from a file of ';' -seperated strings:
class FileInputGenerator10 implements Generator<Input>{
	private Iterator<String> input;
	public FileInputGenerator10(String fileName) {
		input = new TextFile(fileName,";").iterator();
	}
	public Input next() {
		if(!input.hasNext())
			return null;
		return Enum.valueOf(Input.class, input.next().trim());
	}
}
