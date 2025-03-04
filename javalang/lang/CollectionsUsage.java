package javalang.lang;

import java.util.*;

public class CollectionsUsage {
    public static void main(String[] args) {
        /*
        Many Collection functions take List as argument will work for below impl
        * */
        List<Integer> l1 = new ArrayList<>(Arrays.asList(2, 15, 70, 19, 100, 12, 80, 7));
        //List<Integer> l1 = new LinkedList<>(Arrays.asList(2, 15, 70, 19, 100, 12, 80, 7));

        /*
        but won't work with below impl
        * */
        //Queue<Integer> l1 = new LinkedList<>(Arrays.asList(2, 15, 70, 19, 100, 12, 80, 7));
        //Deque<Integer> l1 = new LinkedList<>(Arrays.asList(2, 15, 70, 19, 100, 12, 80, 7));
        Collections.sort(l1);
        l1.stream().forEach(item -> System.out.print(item + ","));
        int ix1 = Collections.binarySearch(l1, 15);
        System.out.println("\ninx" + ix1);
        int ix2 = Collections.binarySearch(l1, 17);
        System.out.println("\ninx" + ix2);
        int px2 = -(ix2 + 1);

        l1.add(px2, 17);
        l1.stream().forEach(item -> System.out.print(item + ","));


        System.out.println("after reversing:");
        Collections.reverse(l1);
        l1.stream().forEach(item -> System.out.print(item + ","));

        List<Integer> subList = l1.subList(1, 4);
        subList.add(2, 75);
        l1.stream().forEach(item -> System.out.print(item + ","));
        // no method like, if you want to use subList
        //Collections.remove()
        // below is out of bound, gives exception
        //subList.remove(5);
        subList.remove(0);
        System.out.println("\nafter removal");
        l1.stream().forEach(item -> System.out.print(item + ","));
        // removes 3 elements which were part of sublist
        subList.clear();
        System.out.println("\nafter clearing");
        l1.stream().forEach(item -> System.out.print(item + ","));

        int minItem = Collections.min(l1);
        int maxItem = Collections.max(l1);
        System.out.println("\nmin: " + minItem + ", " + maxItem);

        // if dest list size is not
        // Exception in thread "main" java.lang.IndexOutOfBoundsException: Source does not fit in dest
        List<Integer> l2 = new ArrayList<>();
        //better to use
        l2 = new ArrayList<>(l1);
        // instead of
        // Collections.copy(l2, l1);

        l2.stream().forEach(item -> System.out.print(item + ","));

        System.out.println("15 times:" + Collections.frequency(l1, 15));
        System.out.println("50 times:" + Collections.frequency(l1, 50));

        /* set collection */
        /*
        below is good collection if query might ask floor, ceil, subset, headSet, tailSet of non existent element
        in set. there sorting list won;t help
        * */
        TreeSet<Integer> ts = new TreeSet<>(l2);
        System.out.println("orig set");
        ts.stream().forEach(item -> System.out.print(item + ","));
        // can find just smaller and larger element of non existent element as well
        int f1 = ts.floor(13);
        int c1 = ts.ceiling(13);
        System.out.println("\n floor" + f1 + " ceil " + c1);
        // for existing element both are same
        int f2 = ts.floor(15);
        int c2 = ts.ceiling(15);
        System.out.println("\n floor" + f2 + " ceil " + c2);

        /* SortedSet type of objects are returned */
        System.out.println("headset:");
        SortedSet<Integer> hs1 = ts.headSet(18);
        hs1.stream().forEach(item -> System.out.print(item + ","));
        System.out.println("subset:");
        SortedSet<Integer> ss1 = ts.subSet(10, 20);
        ss1.stream().forEach(item -> System.out.print(item + ","));
        System.out.println("tailset:");
        SortedSet<Integer> ts1 = ts.tailSet(76);
        ts1.stream().forEach(item -> System.out.print(item + ","));

        /* if quest asks in specific list SortedSet can be converted back to List */
        List<Integer> r1 = new LinkedList<>(ts1);
        r1.stream().forEach(item -> System.out.print(item + ","));

        /* same rules apply for sorted map as well as below */

        TreeMap<Integer, String> tm1 = new TreeMap<>();
        tm1.put(1, "one");
        tm1.put(5, "five");
        tm1.put(7, "seven");

        SortedMap<Integer, String> sm1 = tm1.headMap(3);
        SortedMap<Integer, String> subm1 = tm1.subMap(3, 6);
        SortedMap<Integer, String> tailm1 = tm1.tailMap(6);

    }
}
