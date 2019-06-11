package zz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lyk
 * @Date 2019/5/27 9:55
 * @Version 1.0
 **/
public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, String> paraMap = new HashMap<String, String>();
        paraMap.put("accessKey", "abdsfdsaf");
        paraMap.put("secretKey", "erwoiutouseworperwa");
        System.out.println(paraMap);
        String body = JSON.toJSONString(paraMap, SerializerFeature.WriteMapNullValue);
        System.out.println(body);

        System.out.println("----------------");

        Map map = new HashMap();
        map.put("orderId", 1135804651426283520L);
        System.out.println(map);
        String abc = JSON.toJSONString(map);
        System.out.println(abc);
        JSONObject jsonObject = JSON.parseObject(abc);
        System.out.println(jsonObject.get("orderId"));

        System.out.println("a" == "a");
        String a = "abcc";
        String b = new String("abcc");
        System.out.println(a == b);

        Puppy puppy = new Puppy("cat", 20);
        Puppy puppy2 = new Puppy("cat", 10);
        System.out.println(puppy.getName() == puppy2.getName());

        System.out.println(new BigInteger("0"));
        System.out.println(" ".isEmpty());
        throw new Exception("啦啦德玛西亚");
    }

    //可变参数demo
    public static void print(int... numbers) {
        System.out.println(numbers instanceof int[]);
        System.out.println(numbers.getClass().getTypeName());
    }

    //获取参数类型demo
    public static void getVariableType() {
        System.out.println("a".getClass().getTypeName());
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
