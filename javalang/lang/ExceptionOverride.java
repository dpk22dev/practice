package javalang.lang;

/*
Serializable <- Throwable <- Exception(Checked Exception) <- RuntimeException(Unchecked exception)
Serializable <- Throwable <- Error(don't catch)

Exception or its subclass forces coder to declare/catch exception
 */

/*
The differentiation exists to strike a balance between enforcing error handling at compile time
and providing flexibility to handle runtime issues.
Checked exceptions: good when caller must take responsibility for handling the error
Unchecked exception: good when caller cannot reasonably recover from the error, and fixing the code to handle data which caused
                    this exception is the right solution

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
        try {
            exceptionOverride.genMyError();
        } catch (Throwable throwable) {
            System.out.println(" catching error " + throwable.getMessage());
        }
        try {
            exceptionOverride.genMyException();
        } catch (MyException e) {
            System.out.println( "caught exc " + e.getMessage() );
        }
        try {
            exceptionOverride.genMyRunTimeException();
        } catch (Throwable throwable) {
            System.out.println(" caught runtime exc " + throwable.getMessage());
        }
    }
}
