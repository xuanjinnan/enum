package examples;

public enum SecurityCategory2 {
	STOCK(Security.Stock.class),BOND(Security.Bond.class);
	Security[] values;
	SecurityCategory2(Class<? extends Security> kind){
		values = kind.getEnumConstants();
	}
	interface Security{
		enum Stock implements Security{SHORT,LONG,MARGIN};
		enum Bond implements Security{MUNICIPAL,JUNK};
	}
	
	public Security randomSelection(){
		return Enums.random(values);
	}
	
	public static void main(String[] args) {
		for(int i =0; i < 10; i++){
			SecurityCategory2 category2 = Enums.random(SecurityCategory2.class);
			System.out.println(category2 + ": " + category2.randomSelection());
		}
	}
}
