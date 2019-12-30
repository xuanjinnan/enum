package examples;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

enum Explore{HERE,THERE}

public class Reflection {
	public static Set<String> analyze(Class<?> enumClass){
		System.out.println("-------Analyzing " + enumClass + "----------");
		System.out.println("interfaces:");
		for(Type t : enumClass.getInterfaces())
			System.out.println(t);
		System.out.println("Base:" + enumClass.getSuperclass());
		Set<String> methods = new TreeSet<String>();
		for(Method m :enumClass.getMethods()) {
			methods.add(m.getName());
		}
		System.out.println(methods);
		return methods;
	}
	public static void main(String[] args) {
		Set<String> exploreMthods = analyze(Explore.class);
		Set<String> enumMthods = analyze(Enum.class);
		System.out.println("Explore.containsAll(Enum)? " + exploreMthods.containsAll(enumMthods));
		System.out.println("Explore.removeAll(Enum)? " + exploreMthods.removeAll(enumMthods));
		System.out.println(exploreMthods);
		//Decompile the code for the enum
		OSExcute.command("javap E:\\学习\\thinkInJava_at_git\\enum\\bin\\examples\\Explore.class");
	}
}
