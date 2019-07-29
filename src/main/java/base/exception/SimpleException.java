package base.exception;

public class SimpleException extends Exception {
    public static class InheritingExceptions{
        public void f() throws SimpleException{
            System.out.println("throw simpleException from f()");
            throw new SimpleException();
        }
    }

    public static void main(String[] args) {
        InheritingExceptions inheritingExceptions = new InheritingExceptions();
        try {
            inheritingExceptions.f();
        }catch (SimpleException s){
            System.out.println("Caught it!");
        }
    }
}
