package practies;

public enum Course {
	APPETIZER(Food.Appetizer.class),
	MAINCOURSE(Food.MainCourse.class),
	DESSERT(Food.Dessert.class),
	COFFEE(Food.Coffee.class),
	DRINK(Food.Drink.class);
	private Food[] values;
	private Course(Class<? extends Food> kind) {
		values = kind.getEnumConstants();
	}
	public Food randomSelection() {
		return Enums.random(values);
	}
}
