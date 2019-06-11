package io;

import java.io.File;
import java.util.*;

/**
 * @author lyk
 * @Date 2019/5/29 21:22
 * @Version 1.0
 **/
public class FileTest {
    /**
     * 一个流被定义为一个数据序列。
     * 输入流用于从源读取数据，输出流用于向目标写数据。
     */
    public static void main(String[] args) {
        test1(args);
    }

    public static void test1(String[] args) {
        File path = new File(".");
        String[] list;
        if (args.length == 0) {
            list = path.list();
        } else {
            list = path.list(new DirFilter(args[0]));
        }
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list) {
            System.out.println(dirItem);
        }
    }

}
