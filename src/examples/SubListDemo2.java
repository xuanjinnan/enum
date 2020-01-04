package examples;

import java.util.ArrayList;
import java.util.List;

public class SubListDemo2 {

	public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20000; i++) {
            list.add(i);
        }
        System.out.println("父 list=" + list);
        long starttime1 = System.currentTimeMillis();
        // 总的元素个数
        int total = list.size();
        // 每次处理元素个数
        int pageNo = 500;
        for (int fromIndex = 0; fromIndex < total; ) {
            int toIndex = fromIndex + pageNo;
            List<Integer> subList1 = null;
            if (toIndex <= total) {
                subList1 = list.subList(fromIndex,toIndex);
            } else {
                subList1 = list.subList(fromIndex,total);
            }
            fromIndex = toIndex;
            System.out.println("子 subList1=");
        }
        long endtime1 = System.currentTimeMillis();
        System.out.println("just sublist costitme: " + (endtime1 - starttime1));
//        list.clear();

        System.out.println();
        System.out.println("=====================");
        System.out.println();

        long starttime2 = System.currentTimeMillis();
        // 循环次数
        int count = (total - 1) / pageNo + 1;
        System.out.println("循环次数=" + count);
        for (int i = 0; i < count; i++) {
            List<Integer> subList1 = null;
            int currentSize = list.size();
            if (currentSize >= pageNo) {
                subList1 = list.subList(0, pageNo);
            } else if (currentSize != 0){
                subList1 = list.subList(0, currentSize);
            }
            System.out.println("子 subList1=");
            subList1.clear();
        }
        long endtime2 = System.currentTimeMillis();
        System.out.println("just sublist and sublistclear costitme: " + (endtime2 - starttime2));
    }

}
