package javalang.threading.quest;

public class ZeroEvenOddPrinter {

    public static Object zeroLock = new Object();
    public static int counter = 1;
    public static boolean printZero = false;

    public static void main(String[] args) {

        Runnable zeroPrinter = () -> {
            synchronized (zeroLock) {
                while (!printZero) {
                    try {
                        zeroLock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        };

        Runnable oddPrinter = () -> {

        };

        Runnable evenPrinter = () -> {

        };


    }
}
