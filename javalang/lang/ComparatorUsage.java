package javalang.lang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class ComparatorUsage {

    private static class Comp implements Comparator<List<String>> {
        @Override
        public int compare(List<String> alist, List<String> blist) {
            if (alist.get(0).compareTo(blist.get(0)) == 0) {
                Integer a1 = Integer.parseInt(alist.get(1));
                Integer b1 = Integer.parseInt(blist.get(1));
                return a1.compareTo(b1);
            }
            return alist.get(0).compareTo(blist.get(0));

        }
    }

    public static void main(String[] args) {
        List<List<String>> al = new ArrayList<>();
        al.add(List.of("a", "0100"));
        al.add(List.of("b", "0300"));
        al.add(List.of("a", "0150"));

        //Collections.sort( al, new Comp() );
        Collections.sort(al, (alist, blist) -> {
            if (alist.get(0).compareTo(blist.get(0)) == 0) {
                Integer a1 = Integer.parseInt(alist.get(1));
                Integer b1 = Integer.parseInt(blist.get(1));
                return a1.compareTo(b1);
            }
            return alist.get(0).compareTo(blist.get(0));
        });

        al.stream().forEach(s -> System.out.println(s + " / "));

    }
}
