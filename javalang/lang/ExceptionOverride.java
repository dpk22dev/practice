package javalang.lang;

/*
Serializable <- Throwable <- Exception(Checked Exception) <- RuntimeException(Unchecked exception)
Serializable <- Throwable <- Error(don't catch)

Exception or its subclass forces coder to declare/catch exception
 */

public class ExceptionOverride {
    static class MyException extends Exception{
        public MyException(String message) {
            super(message);
        }
    }

    static class MyRunTimeException extends RuntimeException{
        public MyRunTimeException(String message) {
            super(message);
        }
    }

    static class MyError extends Error{
        public MyError(String message) {
            super(message);
        }
    }

    void genMyError(){
        System.out.println("inside genMyError");
        throw new MyError("my error");
    }

    void genMyException() throws MyException {
        System.out.println("inside genMyException");
        throw new MyException("my exception");
    }

    void genMyRunTimeException(){
        System.out.println("inside genRunTimeException");
        throw new MyRunTimeException("my runtime exception");
    }

    public static void main(String[] args) {
        ExceptionOverride exceptionOverride = new ExceptionOverride();
        //exceptionOverride.genMyError();
        try {
            exceptionOverride.genMyException();
        } catch (MyException e) {
            System.out.println( "caught exc " + e.getMessage() );
        }
        exceptionOverride.genMyRunTimeException();
    }
}
