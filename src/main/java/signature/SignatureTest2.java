package signature;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author lyk
 * @Date 2019/6/5 13:30
 * @Version 1.0
 **/
public class SignatureTest2 {

    public static void main(String[] args) throws UnsupportedEncodingException {
        //单机
        String queryString =
                "AccessKeyId=ItvWwUyji3H6tRyv" +
                        "&Signature=kkx6Av1msTyCN5gQxRhYvQw9cBE%3D" +
                        "&baseUrl=aHR0cHM6Ly90ZXN0MDYxMS5zMy50ZXN0LmNvbS_ogIzmmK_jgIEudHh0&expire=28800" +
                        "&SignatureNonce=ed4a50ba361228c0b5916bf06d2ff45a";
//        getSingatureFromRequest(queryString,"YA77gaiu2wItZqnwtBAcKQl12JARSe");

        //预发布
        String queryString2 =
                "AccessKeyId=IE6vJaB8SUJd1lH6" +
                        "&Signature=kkx6Av1msTyCN5gQxRhYvQw9cBE%3D" ;
//                        "&baseUrl=aHR0cHM6Ly90ZXN0MDYxMS5zMy50ZXN0LmNvbS_ogIzmmK_jgIEudHh0&expire=28800" +
//                        "&SignatureNonce=ed4a50ba361228c0b5916bf06d2ff45a";
        getSingatureFromRequest(queryString2,"HPNjZLxB8aHdwxuKGXrbiIPYIdd6hH");
    }


    public static String percentEncode(String value) throws UnsupportedEncodingException {
        return value != null ? URLEncoder.encode(value, "utf-8").replace("+", "%20")
                .replace("*", "%2A").replace("%7E", "~") : null;
    }

    public static void getSingatureFromRequest(String queryString, String accessSecret) throws UnsupportedEncodingException {
        if (queryString != null) {
            Map temp = new HashMap(2);
            String[] querys = queryString.split("&");
            for (String kvs : querys) {
                String[] kvPairs = kvs.split("=", 2);
                if (kvPairs.length == 1) {
                    temp.put(percentEncode(kvPairs[0]), "");
                } else if (kvPairs.length == 2) {
                    temp.put(percentEncode(kvPairs[0]), percentEncode(kvPairs[1]));
                }
            }
            String signature = (String) temp.get("Signature");
            signature = URLDecoder.decode(signature, "utf-8");
            String accessKeyId = (String) temp.get("AccessKeyId");
            if (signature == null || accessKeyId == null) {
                return;
            }
            System.out.println("Signature: " + signature + ", AccessKeyId: " + accessKeyId);

            //get other parameter from url except signature
            Iterator<Map.Entry<String, String>> it = temp.entrySet().iterator();
            List keys = new ArrayList<String>();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                if (!"Signature".equals(entry.getKey())) {
                    keys.add(entry.getKey());
                }
            }
            //sort parameters
            Collections.sort(keys);
            //canonicalize parameters
            String canonicalizedQueryString = "";
            for (Object key : keys) {
                canonicalizedQueryString += key + "=" + temp.get(key) + "&";
            }
            canonicalizedQueryString = canonicalizedQueryString.substring(0, canonicalizedQueryString.length() - 1);
            String stringToSign = "GET" + "&" + percentEncode("/") + "&" + percentEncode(canonicalizedQueryString);
            stringToSign = "GET&%2F&%3D%26AccessKeyId%3DIE6vJaB8SUJd1lH6%26SignatureNonce%3Ded4a50ba361228c0b5916bf06d2ff45a%26baseUrl%3DaHR0cHM6Ly90ZXN0MDYxMS5zMy50ZXN0LmNvbS8xLmpwZw~~%26expire%3D28800%26test%3D";
            System.out.println("stringToSign: " + stringToSign);
            //Base64 encode
            Mac mac;
            try {
                SecretKeySpec secret_key = new SecretKeySpec((accessSecret + "&").getBytes(), "HmacSHA1");
                mac = Mac.getInstance(secret_key.getAlgorithm());
                mac.init(secret_key);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return;
            } catch (InvalidKeyException e) {
                e.printStackTrace();
                return;
            }
            byte[] signData = mac.doFinal(stringToSign.getBytes());
            String signedStr = percentEncode(Base64.encodeBase64String(signData));
            if (!signature.equals(signedStr)) {
                System.out.println("signature is not same, queried: " + signature + ", signed: " + signedStr);
                return;
            }
            System.out.println("signature is same, queried: " + signature + ", signed: " + signedStr);
            return;
        }
        System.out.println("queryString is null");
        return;
    }

}