package javalang.faltu;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/*
without overriding hashcode, equals
 */

public class HashCodeTesting {

    static interface KeyIface {
        String getKeyName();
    }

    static abstract class AbstractKey implements KeyIface {
        Integer intKey;
    }

    static class StringifiedKey extends AbstractKey {

        String keyName;

        public StringifiedKey(String keyName, int k) {
            this.keyName = keyName;
            this.intKey = k;
        }

        @Override
        public String getKeyName() {
            return keyName;
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

        System.out.println( keyIface1.hashCode() + " , " + keyIface2.hashCode() );
        System.out.println( "equal: " + keyIface1.equals( keyIface2 ) );

        // it overrides the alpha with beta
        map.put( keyIface2, "beta");
        System.out.println( map );

        // it requires casting but all of them point to same memory location so hashcode is same
        AbstractKey abstractKey1 = (AbstractKey) keyIface2;
        System.out.println( abstractKey1.hashCode() + " , " + keyIface1.hashCode() + " , " + keyIface2.hashCode() );
        System.out.println( "equal: " + keyIface1.equals( keyIface2 ) );

        Deque deque;
        LinkedList ll;


    }

}
