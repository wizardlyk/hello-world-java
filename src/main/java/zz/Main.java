package zz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author lyk
 * @Date 2019/5/27 9:55
 * @Version 1.0
 **/
public class Main {
    public static void main(String[] args) {
        //1
        int sku = 0;
        sku = parseDataDisk("UNIS_PC_TJ_A_DATADISK_NORMAL-1T");
        sku = parseDataDisk("UNIS_PC_TJ_A_DATADISKHOUR_NORMAL-50GB");
//        System.out.println(sku);
        //2
        String desc = "{\"billingMethod\":\"包年包月\",\"conf\":{\"地域\":\"天津\",\"硬盘规格\":\"高性能HDD云硬盘 50GB\",\"计费模式\":\"包年包月\"}}";
        JSONObject jsonObject = JSONObject.parseObject(desc);
        JSONObject conf = jsonObject.getJSONObject("conf");
        desc = conf.getString("硬盘规格");
//        System.out.println(desc);

        //4
//        foreach();

        //5
        BigDecimal rentCount = new BigDecimal("73008").multiply(new BigDecimal("120"));
        MathContext mc = new MathContext(2, RoundingMode.HALF_DOWN);
        BigDecimal mounth = rentCount.divide(new BigDecimal("73008"), mc);
//        System.out.println(mounth);
//        System.out.println(mounth.toPlainString());

        //6
        test1();
        List list = new ArrayList();
        System.out.println(list.size());
    }

    private static void test1() {
        List<Long> successIds = new ArrayList<>();
        successIds.add(4454545L);
        successIds.add(444L);
        successIds.add(777L);
        successIds.add(666L);

    }

    private static void test() {
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        list.forEach(e -> {
            if (e.equals("4")) {
                System.out.println("???");
                return;
            }
            System.out.println(e);
        });
    }

    public static void foreach() {
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add("4");
        list.add(null);
        list.add("5");

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                list.remove(i);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static Date fomatDate(String startTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date result = sdf.parse(startTime);
        return result;
    }

    public static int parseDataDisk(String sku) {
        String regEx = ".*DATADISK_(?:NORMAL|HC|HIGHIO)-\\d{1,3}(GB|T)";
        String regEx1 = ".*DATADISKHOUR_(?:|NORMAL|HC|HIGHIO)-\\d{1,3}(GB|T)";
        Pattern pattern = Pattern.compile(regEx);
        Pattern pattern1 = Pattern.compile(regEx1);

        Matcher matcher = pattern.matcher(sku);
        Matcher matcher1 = pattern1.matcher(sku);
        boolean flag = matcher.matches();
        boolean flag1 = matcher1.matches();
        if (flag && flag1) {
            return -1;
        }
        String result = sku.substring(sku.indexOf("-") + 1);
        if (result.indexOf("GB") != -1) {
            return new Integer(result.substring(0, result.length() - 2)).intValue();
        } else {
            return new Integer(result.substring(0, result.length() - 1)).intValue() * 1024;
        }
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
