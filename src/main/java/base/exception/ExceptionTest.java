package base.exception;

/**
 * @author lyk
 * @Date 2019/5/29 21:37
 * @Version 1.0
 **/
public class ExceptionTest {
    /**
     * 异常发生的原因有很多，通常包含以下几大类：
     * <p>
     * 用户输入了非法数据。
     * 要打开的文件不存在。
     * 网络通信时连接中断，或者JVM内存溢出。
     * <p>
     * 检查性异常：最具代表的检查性异常是用户错误或问题引起的异常，这是程序员无法预见的。例如要打开一个不存在文件时，一个异常就发生了，这些异常在编译时不能被简单地忽略。
     * 运行时异常： 运行时异常是可能被程序员避免的异常。与检查性异常相反，运行时异常可以在编译时被忽略。
     * 错误： 错误不是异常，而是脱离程序员控制的问题。错误在代码中通常被忽略。例如，当栈溢出时，一个错误就发生了，它们在编译也检查不到的。
     */
    public static void main(String[] args) {
        Throwable throwable = new Throwable();
        RuntimeException runtimeException = new RuntimeException("运行时异常");
        System.out.println(runtimeException);
        runtimeException.printStackTrace();

        ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException = new ArrayIndexOutOfBoundsException("数组下标越界");
        System.out.println(arrayIndexOutOfBoundsException);
        arrayIndexOutOfBoundsException.printStackTrace();

        test();
    }

   static void test() throws NullPointerException{
        return;
    }
}
