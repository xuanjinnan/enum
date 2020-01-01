package examples;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.EnumSet;
public class BigEnumSet {
	enum Big{	A0,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,
		A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,
		A21,A22,A23,A24,A25,A26,A27,A28,A29,A30,
		A31,A32,A33,A34,A35,A36,A37,A38,A39,A40,
		A41,A42,A43,A44,A45,A46,A47,A48,A49,A50,
		A51,A52,A53,A54,A55,A56,A57,A58,A59,A60,
		A61,A62,A63,A64,
		A65,A66,A67,A68,A69,A70,A71,A72,A73,A74,A75
	}
	public static void main(String[] args) throws Exception {
		EnumSet<Big> bigEnumSet = EnumSet.allOf(Big.class);
		Class<? extends EnumSet> class1 = bigEnumSet.getClass();
		String nameVlues="";
		Object obj[] = new Object[0];
		//得到属性
		Field field = class1.getDeclaredField("elements");
		//打开私有访问
		field.setAccessible(true);
		//获取属性
		String name = field.getName();
		//获取属性值
		Object value = field.get(bigEnumSet);
		//一个个赋值
		System.out.println(value);
		System.out.println(((long[])value)[0]);
		System.out.println(((long[])value)[1]);
		System.out.println(((long[]) value).length);
	}
	/*Field declaredField = class1.getDeclaredField("elements");
		declaredField.setAccessible(true);
		System.out.println(declaredField);
		Long elements[] = new Long[0];
		System.out.println(declaredField.get(elements));*/
}
