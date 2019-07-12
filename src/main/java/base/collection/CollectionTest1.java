package base.collection;

import java.util.*;

/**
 * @author lyk
 * @Date 2019/6/27 16:04
 * @Version 1.0
 **/
public class CollectionTest1 {
    public static void main(String[] args) {

        ergodicMap();
    }

    private static void ergodicMap() {
        Map<String, String> map = new HashMap<String, String>(3);
        map.put("1", "value1");
        map.put("2", "value2");
        map.put("3", "value3");
        //1
        for (String key : map.keySet()) {
            System.out.printf("键: %s,值: %s %n", key, map.get(key));
        }
        //2
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.printf("键: %s,值: %s %n", entry.getKey(), entry.getValue());
        }
        //3
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.printf("键: %s,值: %s %n", entry.getKey(), entry.getValue());
        }
        //4
        for (String str : map.values()) {
            System.out.printf("值: %s %n", str);
        }
    }

    private static void ergodicList() {
        List<String> list = new ArrayList();
        list.add("hello");
        list.add("world");
        list.add("砸瓦鲁多");
        //2
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
    }
}
