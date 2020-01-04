package examples;

import java.util.ArrayList;
import java.util.List;
class Vo{
	long sublist;
	long add;
	@Override
	public String toString() {
		return "Vo [sublist=" + sublist + ", add=" + add + "]";
	}
	
}
public class SubListDemo {
	public static void main(String[] args) {
		// batch = 500
		int batch = 500;
		List<Vo> list = new ArrayList<Vo>();
		for(int i = 0; i < 10000; i++) {
			long sublistCost = sublistCost(batch);
			long addCost = addCost(batch);
			Vo vo = new Vo();
			vo.sublist = sublistCost;
			vo.add = addCost;
			list.add(vo);
		}
		System.out.println("first field  is sublist,second field is add");
		System.out.println(list);
		long sublistCostAll = 0;
		long addCostAll = 0;
		for (Vo vo : list) {
			sublistCostAll +=  vo.sublist;
			addCostAll += vo.add;
		}
		System.out.println("sublistCostAll: " + sublistCostAll + " addCostAll: " + addCostAll);
	}
	private static List<String> getStringList(int size){
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < size; i ++) {
			list.add(String.valueOf(i));
		}
		return list;
	}
	private static long sublistCost(int batch) {
		//  test subList
		List<String> list = getStringList(10020);
		int size = list.size();
		int time = size/batch;
		List<String> temp = null;
		long starttime = System.currentTimeMillis();
		for(int i = 0; i  < time; i++) {
			if(i != time -1) {
				temp = list.subList(0, batch);
				System.out.println("do insert");// seen print as  do insert
			}else{//最后一次
				temp = list.subList(0, size - i*batch);// seen print as  do insert
				System.out.println("do insert");
			}
			temp.clear();
			System.out.println(list);
		}
		System.out.println("after subList list.size():" + list.size());
		long entime = System.currentTimeMillis();
		return entime - starttime;
	}
	private static long addCost(int batch) {
		//test add 
		List<String> list2 = getStringList(10020);
		long starttime1 = System.currentTimeMillis();
		List<String> temp2 = new ArrayList<String>(batch);
		for(int i = 0; i  < list2.size(); i++) {
			temp2.add(list2.get(i));
			if(temp2.size() == 500 || i == list2.size() -1) {
				System.out.println("do insert");// seen print as  do insert
				temp2.clear();
			}
		}
		long entime1 = System.currentTimeMillis();
		System.out.println("after add list.size():" + list2.size());
		return entime1 - starttime1;
	}
}
