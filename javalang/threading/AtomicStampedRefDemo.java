package javalang.threading;

import java.util.concurrent.atomic.AtomicStampedReference;

class AtomicStampedRefDemo {

    public static void main(String args[]) {

        Long myLong = 3L;
        Long anotherLong = 7L;

        // set the initial stamp to 1 and reference to myLong
        AtomicStampedReference<Long> atomicStampedReference = new AtomicStampedReference<>(myLong, 1);

        // we attempt to change the object reference but use the incorrect stamp and the compareAndSet fails
        boolean result = atomicStampedReference.compareAndSet(myLong, anotherLong, 0, 1);
        System.out.println("Was compareAndSet() successful : " + result + " and object value is " + atomicStampedReference.getReference().toString());

        // we attempt compareAndSet again with the right expected stamp
        result = atomicStampedReference.compareAndSet(myLong, anotherLong, 1, 2);
        System.out.println("Was compareAndSet() successful : " + result + " and object value is " + atomicStampedReference.getReference().toString());

        // Retrieve the current stamp and reference using the get() method
        int[] currStamp = new int[1];
        Long reference = atomicStampedReference.get(currStamp);
        System.out.println("current stamp " + currStamp[0] + " reference value " + reference.toString());
    }
}