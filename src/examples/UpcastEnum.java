package examples;
enum Search{HITHER,YON}

public class UpcastEnum {
	public static void main(String[] args) {
		Search[] vals = Search.values();
		Enum e = Search.HITHER;
		//e.values();//no values() in Enum
		for(Enum en : e.getClass().getEnumConstants()) {
			System.out.println(en);
		}
		
	}
}
