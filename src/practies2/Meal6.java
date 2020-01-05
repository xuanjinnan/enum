package practies2;

import practies.Enums;

interface Food {}

enum Appetizer implements Food {
	SALAD, SOUP, SPRING_ROLLS;
	}
enum MainCourse implements Food {
	LASAGNE, BURRITO, PAD_THAI,
	LENTILS, HUMMOUS, VINDALOO;
}
enum Dessert implements Food {
	TIRAMISU, GELATO, BLACK_FOREST_CAKE,
	FRUIT, CREME_CARAMEL;
}
enum Coffee implements Food {
	BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
	LATTE, CAPPUCCINO, TEA, HERB_TEA;
}

public enum Meal6 {
	APPETIZER(Appetizer.class),
	MAINCOURSE(MainCourse.class),
	DESSERT(Dessert.class),
	COFFEE(Coffee.class);
	private Food[] values;
	private Meal6(Class<? extends Food> kind) {
		values = kind.getEnumConstants();
	}	
	public Food randomSelection() {
		return Enums.random(values);
	}
	public static void main(String[] args) {
		for(int i = 0; i < 5; i++) {
			for(Meal6 meal: Meal6.values()) {
				Food food = meal.randomSelection();
				System.out.println(food);
			}
			System.out.println("---");
		}
	}
}