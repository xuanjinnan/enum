package examples;
enum LikeClasses{
	WINKEN {
		@Override
		void behavior() {
			System.out.println("Behavior1");
		}
	},
	BLINKEN{
		@Override
		void behavior() {
			System.out.println("Behavior2");
		}
	},
	NOD{
		@Override
		void behavior() {
			System.out.println("Behavior3");	
		}
	};
	abstract void behavior();
}
public class NotClasses {
	/*void f1(LikeClasses.NOD instance) {
		instance.behavior();
	};nope*/
	public static void main(String[] args) {
		for(LikeClasses nc : LikeClasses.values()) {
			nc.behavior();
		}
		OSExcute.command("javap E:\\学习\\thinkInJava_at_git\\enum\\bin\\examples\\NotClasses$LikeClasses.class");
		OSExcute.command("javap E:\\学习\\thinkInJava_at_git\\enum\\bin\\examples\\NotClasses$LikeClasses$1.class");
		OSExcute.command("javap E:\\学习\\thinkInJava_at_git\\enum\\bin\\examples\\NotClasses$LikeClasses$2.class");
		OSExcute.command("javap E:\\学习\\thinkInJava_at_git\\enum\\bin\\examples\\NotClasses$LikeClasses$3.class");
	}
}
