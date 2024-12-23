package javalang.generics;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

// P,Q, R are used as member variable in class, that's why defined here. some methods may have different types
// eg transform
public class BasicGeneric<P, Q, R> {

    // not allowed to create static member
    // private static T member;
    P parr[];
    Q qarr[];
    R rarr[];

    int inx = 0;

    // not allowed to extend exception, runtimeexception
    //public class GenericClass<T> extends RuntimeException {}

    public BasicGeneric(int size, Q inpQArr[], R inpRArr[]) {
        // not allowed due to type erasure.
        // parr = new P[size];
        // workaround is to instantiate object array and cast into P type
        parr = (P[]) new Object[size];
        // obj = new T();	//Not Allowed
        qarr = inpQArr;
        rarr = inpRArr;
    }

    public void addItem(P p) {
        parr[inx++] = p;
    }

    public void printArr() {
        for (P p : parr) {
            if (p != null)
                System.out.println(p.toString());
        }

        for (Q q : qarr) {
            System.out.println(q.toString());
        }

        for (R r : rarr) {
            System.out.println(r.toString());
        }

    }

    // define type of method before return type, here <T> before return type defines type used in this method
    // This is needed even if the method is returning void.
    public <T> List<T> fromArrayToList(T[] arr) {
        return Arrays.stream(arr).toList();
    }

    // below method takes input independent of class
    public <X, Y, Z> Z transform(X x, Y y, Z z) {
        System.out.println("x: " + x.toString() + " y: " + y.toString());
        return z;
    }

    // T type defines that it will be equal/subclass of Number. Only T is being used in this method
    public <T extends Number> List<T> fromNumArrayToList(T[] arr) {
        return Arrays.stream(arr).toList();
    }

    // <T extends Number & Comparable> T is bounded by Number and Comparable both
    public <T extends Number & Comparable> List<T> fromCompNumArrayToList(T[] arr) {
        return Arrays.stream(arr).toList();
    }

    //  We know that Object is the supertype of all Java classes.
    //  However, a collection of Object is not the supertype of any collection.
    // List<Object> is not the supertype of List<String>
    // fun(List<Building> buildings) <-- you can't pass List<House> here, even though House extends Building
    /*
    public static void paintAllBuildings(List<? extends Building> buildings) {

    }
    */

    /*
    Type erasure removes all type parameters and replaces them with their bounds
    or with Object if the type parameter is unbounded.
    public <T> List<T> genericMethod(List<T> list) {
        return list.stream().collect(Collectors.toList());
    }
    gets converted to below:
    public List<Object> withErasure(List<Object> list) {
        return list.stream().collect(Collectors.toList());
    }

    public <T extends Building> void genericMethod(T t) {
        ...
    }
    gets converted to below:
    public void genericMethod(Building t) {
       ...
    }

     */
    public static void main(String[] args) {
        String[] strArr = {"alpha", "beta", "delta"};
        Double[] doubles = {1.1, 2.3, 3.3};
        BasicGeneric<Integer, String, Double> basicGeneric = new BasicGeneric<>(3, strArr, doubles);
        basicGeneric.addItem(1);
        basicGeneric.printArr();
        Integer integer = 1;
        Double d = 1.1;
        String res = basicGeneric.<Integer, Double, String>transform(integer,
                d,
                new String("one"));

        HashMap<Integer, Integer> hm;
        Function f;

    }

}
