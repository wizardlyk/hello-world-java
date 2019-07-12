package base.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author lyk
 * @Date 2019/5/29 14:44
 * @Version 1.0
 **/
public class StreamTest {
    public static void main(String[] args) throws IOException {
        printToConsole();

        readCharFromConsole();
        readStringFromConsole();
    }

    /**
     * 读取控制台输入
     *
     * @throws IOException
     */
    public static void readCharFromConsole() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter characters, 'q' to quit.");
        char c;
        do {
            c = (char) br.read();
            System.out.println(c);
        } while (c != 'q');
    }

    public static void readStringFromConsole() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter lines of text,'quit' to quit");
        String s;
        do {
            s = br.readLine();
            System.out.println(s);
        } while (!s.equals("quit"));
    }

    /**
     * 输出控制台（不常用)
     * @throws IOException
     */
    public static void printToConsole() throws IOException {
        System.out.write("legendary".getBytes());
        System.out.write('\n');
    }
}
