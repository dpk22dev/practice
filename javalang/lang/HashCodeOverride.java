package javalang.lang;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/*
if you don't override hashcode, equals then memory location is used in hashcode. so different objects in heap will cause
different entries in hashmap. interface/abstract/actual class reference to same object so entries with any of these keys
will overwrite same value in hashmap
when hashcode, equals is overridden the jvm understands that even though those are different objets in heap, they mean
same object. single entry is overwritten in hashmap. interface/abstract/actual class reference to same entry in hashmap
even though they can refer to different objects in heap
Even though interface reference is used to point to class, subclass. hashcode, equals of different concrete classes result
in different hashcode, equals causing multiple entries in hashmap

below method are used for generating hash, checking equality of objects
Objects.hash
Objects.equals

super.someMethod() allows to call parent class's method explicitely if that method is overridden in child class

 */

public class HashCodeOverride {


    static interface KeyIface {
        String getKeyName();

    }

    static abstract class AbstractKey implements KeyIface {
        Integer intKey;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AbstractKey that = (AbstractKey) o;
            return Objects.equals(intKey, that.intKey);
        }

        @Override
        public int hashCode() {
            return Objects.hash(intKey);
        }
    }

    static class StringifiedKey extends AbstractKey {

        String keyName;

        public StringifiedKey(String keyName, int k) {
            this.keyName = keyName;
            this.intKey = k;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            StringifiedKey that = (StringifiedKey) o;
            return Objects.equals(keyName, that.keyName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), keyName);
        }

        @Override
        public String getKeyName() {
            return keyName;
        }
    }

    static class NamedStringifiedKey extends StringifiedKey{
        double d;
        public NamedStringifiedKey(String keyName, int k, double d) {
            super(keyName, k);
            this.d = d;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            NamedStringifiedKey that = (NamedStringifiedKey) o;
            return Double.compare(d, that.d) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), d);
        }
    }

    public static void main(String[] args) {
        Map<KeyIface, String> map = new HashMap<>();
        KeyIface keyIface1; //= new StringifiedKey("one", 1);
        // Java's default hashCode() method, which is based on the memory address of the object
        keyIface1 = new StringifiedKey("one", 1);
        map.put( keyIface1, "alpha");
        //keyIface2 = new StringifiedKey("one", 1);
        KeyIface keyIface2 = keyIface1;

        // it overrides the alpha with beta
        map.put( keyIface2, "beta");
        System.out.println( map );

        // it requires casting but all of them point to same memory location so hashcode is same
        AbstractKey abstractKey1 = (AbstractKey) keyIface2;
        System.out.println( abstractKey1.hashCode() + " , " + keyIface1.hashCode() + " , " + keyIface2.hashCode() );
        System.out.println( "equal: " + keyIface1.equals( abstractKey1 )  );


        /* you can create key of any interface/abstract class
        but equals, hashcode will be used of the actual object

         */

        KeyIface k1 = new StringifiedKey("two", 2);
        AbstractKey k2 = new StringifiedKey("two", 2);
        StringifiedKey k3 = new StringifiedKey("two", 2);
        System.out.println( k1.hashCode() + " , " + k2.hashCode() + " , " + k3.hashCode() );
        System.out.println( "equal: " + k1.equals( k2 ) + ", " + k2.equals( k3 ) );

        map.put( k1, "p");
        map.put( k2, "q");
        map.put( k3, "r");



        KeyIface k11 = new StringifiedKey("two", 2);
        KeyIface k22 = new NamedStringifiedKey("two", 2, 2.2);
        System.out.println( k11.hashCode() + " , " + k22.hashCode() );
        System.out.println( "equal: " + k11.equals( k22 ) );

        map.put( k11, "p11");
        map.put( k22, "p22");

        System.out.println(map);
    }

}
