package examples;

import examples.Food.Appetizer;
import examples.Food.Coffee;
import examples.Food.Dessert;
import examples.Food.MainCourse;

public class TypeOfFood {
	public static void main(String[] args) {
		Food food = Appetizer.SALAD;
		food = MainCourse.LASAGEN;
		food = Dessert.GELATO;
		food = Coffee.CAPPUCCINO;
	}
}
