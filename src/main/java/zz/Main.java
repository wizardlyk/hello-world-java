package zz;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lyk
 * @Date 2019/5/27 9:55
 * @Version 1.0
 **/
public class Main {
    public static void main(String[] args) {
        System.out.println("a".getClass().getTypeName());
        print(54, 45, 5, 5);

        Map<String, String> paraMap = new HashMap<String, String>();
        paraMap.put("accessKey", "abdsfdsaf");
        paraMap.put("secretKey", "erwoiutouseworperwa");
        System.out.println(paraMap);
    }

    //可变参数
    public static void print(int... numbers) {
        System.out.println(numbers instanceof int[]);
        System.out.println(numbers.getClass().getTypeName());
    }

    //在对象被垃圾收集器析构(回收)之前调用，它用来清除回收对象。
    @Override
    protected void finalize() {
        // 在这里终结代码
        System.out.println("abc");
        System.gc();
        System.out.println("def");
    }

}