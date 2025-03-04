package javalang.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamExample {

    private static class Product {
        int id;
        String item;

        public Product(int i, String potatoes) {
            id = i;
            item = potatoes;
        }
    }

    public static void main(String[] args) {
        Stream<String> ss1 = Stream.empty();
        String strArr[] = {"a", "b", "c"};
        ss1 = Arrays.stream(strArr);
        Collection<String> cs1 = Arrays.asList(strArr);

        //cs1.forEach( System.out::println );
        //ss1.forEach(System.out::println );
//        Stream.of( "a", "b", "c"  ).forEach( System.out::println );

        /* ways to create stream statically */
        Stream<String> aStream = Stream.<String>builder().add("a").build();
//        aStream.forEach( System.out::println );
        aStream = Stream.generate(() -> "e").limit(10);
//        aStream.forEach( System.out::println );

        /*
        Intstream examples
        * */
        Stream<Integer> si1 = Stream.iterate(10, (x) -> x + 1).limit(10);
        //si1.forEach( System.out::println );

        //IntStream.range( 1, 10 ).forEach( System.out::println );
        //IntStream.iterate( 1, (x) -> x*2 ).limit(10).forEach( System.out::println );
        int resi1 = IntStream.iterate(1, (x) -> x * 2).limit(10).reduce(100, (x, y) -> x + y);
        //System.out.println( resi1 );

        Stream<String> ss2 = Arrays.asList("abc111", "abc222", "abc333")
                .stream()
                .map(e -> e.substring(0, 4))
                .filter(e -> e.endsWith("2"));
        Optional<String> res = ss2.findAny();
        //res.ifPresent( System.out::println );

        /*
        collectors
        * */
        List<Product> productList = Arrays.asList(new Product(23, "potatoes"),
                new Product(14, "orange"), new Product(13, "lemon"),
                new Product(26, "bread"), new Product(14, "sugar"));
        Stream<Product> eStream = productList.stream().filter(p -> p.id % 2 == 0);
        //List<Product> eProductList = eStream
        //        .collect(Collectors.toList() );
        //eProductList.stream().forEach( p -> System.out.println( p.id ) );

        //eStream.collect(Collectors.toUnmodifiableSet()).forEach( p -> System.out.println( p.id ) );
        //eStream.collect(Collectors.toCollection( LinkedList::new ) );

        Map<Integer, String> map1 = eStream.collect(toMap(
                (p) -> p.id, // generates key from object
                (p) -> p.item, // generates value from object
                (x, y) -> x.length() > y.length() ? x : y)); // if x, y values are found corresponding to same key
//        System.out.println( map1 );

//        long count1 = eStream.collect( counting() );
//        System.out.println( count1 );

        /*
        group by specific key
        * */
        Map<Integer, List<Product>> list1 = eStream.collect(groupingBy(p -> p.id, toList()));
//        System.out.println( list1 );

        /*
        flat map is used to stream elments internal to collection
         */
        List<List<String>> lls1 = Arrays.asList(
                Arrays.asList("1", "11", "111"),
                Arrays.asList("2", "22", "222"),
                Arrays.asList("3", "33", "333")
        );
        lls1.stream().flatMap(lizt -> lizt.stream()).filter(x -> x.length() > 2).forEach(System.out::println);

        /*
        // flatmap can be used to flatten values of internal map
        // HashMap<String, HashMap<String, Integer>>
                List<Integer> allValues = outerMap.values().stream()
                                            .flatMap(innerMap -> innerMap.values().stream())
                                            .collect(Collectors.toList());
         */

        /* feed integers/numbers to reducing into int */
        int sum = productList.stream()
                .map(p -> p.id)
                .collect(Collectors.reducing(0, (a, b) -> a + b));

        /* map something of object to collect into list of thing */
        List<String> nameLengths = productList.stream()
                .collect(Collectors.mapping(p -> p.item, Collectors.toList()));

        Map<String, Integer> modifiableMap = Map.of("one", 1);
        Collections.unmodifiableMap(modifiableMap);
    }
}
