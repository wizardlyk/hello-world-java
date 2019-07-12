package base.io;

import java.util.Scanner;

/**
 * @author lyk
 * @Date 2019/5/29 21:27
 * @Version 1.0
 **/
public class ScannerTest {
    /**
     * java.util.Scanner是Java5的新特征，我们可以通过 Scanner 类来获取用户的输入。
     */
    public static void main(String[] args) {
        nextIntTest();
    }

    public static void nextTest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("next:");
        if (scanner.hasNext()) {
            String s = scanner.next();
            System.out.println("your input: " + s);
        }
    }

    public static void nextLineTest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("next:");
        if (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            System.out.println("your input: " + s);
        }
    }

    public static void nextIntTest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("next:");
        int i = 0;
        if (scanner.hasNextInt()) {
            i = scanner.nextInt();
            System.out.println("your input: " + i);
        }
    }
}
