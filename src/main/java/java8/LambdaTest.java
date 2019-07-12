package java8;

import zz.Puppy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lyk
 * @Date 2019/6/27 14:43
 * @Version 1.0
 **/
public class LambdaTest {
    public static void main(String[] args) {
        Map<String, List<Puppy>> map = new HashMap<>(1);
        List<Puppy> puppies = new ArrayList<>(2);
        puppies.add(new Puppy("doggie", 1));
        puppies.add(new Puppy("chicken", 3));
        map.put("PuppyList", puppies);
        map.put("test", null);

        map.keySet().stream().forEach(a -> {
            System.out.println(a);
            System.out.println(map.get(a));
        });
        System.out.println("=========");
        System.out.println(map.keySet());
        System.out.println(map.entrySet());
    }
}
