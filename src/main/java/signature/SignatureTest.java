package signature;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import org.apache.commons.codec.binary.Base64;

/**
 * @author lyk
 * @Date 2019/6/5 13:30
 * @Version 1.0
 **/
public class SignatureTest {

    public static void main(String[] args) throws UnsupportedEncodingException {

//        //线上
//        String queryString1 =
//                "AccessKeyId=zsVhbfJQ17yOpQI8" +
//                        "&Signature=dPctz7IC5g66qq277%2FXh0Bk6vt0%3D" +
//                        "&baseUrl=aHR0cHM6Ly91bmljbG91ZC5vc3MtY24tbm9ydGgtMS51bmljbG91ZHNydi5jb20vMS5wbmc~" +
//                        "&expire=28800" +
//                        "&SignatureNonce=ed4a50ba361228c0b5916bf06d2ff45a" +
//                        "&test=helloworld";
//        getSingatureFromRequest(queryString1, "H7X1FfF0M3jguHXpc0Qk3dT6MlDM6M");
//
//        //预发布
//        String queryString2 = "AccessKeyId=1kAoUHommgWm5cTa" +
//                "&Signature=hJgp2BSuArrlrwtZpsqNkJp0%2Fq4%3D" +
//                "&baseUrl=+";
//                "&expire=28800" +
//                "&SignatureNonce=ed4a50ba361228c0b5916bf06d2ff45a";
//        getSingatureFromRequest(queryString2, "u377ne1QlHTTKpQjvWaYvhnFcRvrdr");

        //单机
//        String queryString3 = "AccessKeyId=ItvWwUyji3H6tRyv" +
//                "&Signature=gzRcrXXLSM8LP8cEKqdy2sL%2B8LU%3D" +
//                "&baseUrl=aHR0cHM6Ly91bmljbG91ZC5vc3MtY24tbm9ydGgtMS51bmljbG91ZHNydi5jb20vMS5wbmc~" +
//                "&expire=28800" +
//                "&SignatureNonce=ed4a50ba361228c0b5916bf06d2ff45a";
//        getSingatureFromRequest(queryString3, "YA77gaiu2wItZqnwtBAcKQl12JARSe");

        String baseUrl = decryptBase64ToStdFormat("aHR0cHM6Ly90ZXN0MDYxMS5zMy50ZXN0LmNvbS9yb3V0ZXIudHh0");
        byte[] base64decodedBytes = java.util.Base64.getDecoder().decode(baseUrl);
        String origionUrl = "";
        try {
            origionUrl = new String(base64decodedBytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
//        System.out.println(origionUrl);

        //CDN
//        String queryString4 = "AccessKeyId=yE4IdbNciD7ZKMLL" +
//                "&Signature=XjRglWuyabNo9iEtg8qXZK5pXoY%3D" +
//                "&baseUrl=aHR0cHM6Ly91bmljbG91ZC5vc3MtY24tbm9ydGgtMS51bmljbG91ZHNydi5jb20vMS5wbmc~" +
//                "&expire=28800" +
//                "&SignatureNonce=ed4a50ba361228c0b5916bf06d2ff45a" +
//                "&test=hello+world";
//        getSingatureFromRequest(queryString4, "hO5DcRvIwDgMPy7vgOnvq37yPXeAPQ");

//        String stringToSign = URLEncoder.encode("!@#$%^&*()+-_=/. \"", "utf-8");
//        System.out.println(stringToSign);


        //ys
        String queryString5 = "AccessKeyId=IE6vJaB8SUJd1lH6" +
                "&Signature=QnTA4ifbs8Y2QJ35SEED6qcov8s%3D" +
                "&baseUrl=aHR0cHM6Ly90ZXN0MDYxMS5zMy50ZXN0LmNvbS_ogIzmmK_jgIEudHh0" +
                "&expire=28800" +
                "&SignatureNonce=ed4a50ba361228c0b5916bf06d2ff45a" +
//                "&test=b~b+b.b-b_bb=b" ;
                "&test=g*g" ;
//        String temp = "AccessKeyId=IE6vJaB8SUJd1lH6&Signature=QnTA4ifbs8Y2QJ35SEED6qcov8s%3D&baseUrl=aHR0cHM6Ly90ZXN0MDYxMS5zMy50ZXN0LmNvbS_ogIzmmK_jgIEudHh0&expire=28800&SignatureNonce=ed4a50ba361228c0b5916bf06d2ff45a&test=b~b+b.b-b_b%2Ab=b";
//        System.out.println(queryString5.equals(temp));
        getSingatureFromRequest(queryString5, "HPNjZLxB8aHdwxuKGXrbiIPYIdd6hH");
    }


    public static void getSingatureFromRequest(String queryString, String accessSecret) throws UnsupportedEncodingException {
        //ak sk verification
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
            String accessKeyId = (String) temp.get("AccessKeyId");
            if (signature == null || accessKeyId == null) {
                System.out.println("Signature or AccessKeyId is null");
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
            System.out.println(canonicalizedQueryString);
//            String stringToSign = "GET" + "&" + URLEncoder.encode("/", "utf-8") + "&" + URLEncoder.encode(canonicalizedQueryString, "utf-8");
            String stringToSign = "GET" + "&" + percentEncode("/") + "&" + percentEncode(canonicalizedQueryString);
            System.out.println("stringToSign: " + stringToSign);
//            String newStringToSign = stringToSign.replaceAll("%7E", "~").replaceAll("\\*", "%2A").replaceAll("\\+", "%20");
//            System.out.println("newStringToSign: " + newStringToSign);
            //Base64 encode
            Mac mac;
            try {
                SecretKeySpec secret_key = new SecretKeySpec((accessSecret + "&").getBytes(), "HmacSHA1");
                mac = Mac.getInstance(secret_key.getAlgorithm());
                mac.init(secret_key);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                System.out.println("NoSuchAlgorithmException");
                return;
            } catch (InvalidKeyException e) {
                System.out.println("InvalidKeyException");
                e.printStackTrace();
                return;
            }
            byte[] signData = mac.doFinal(stringToSign.getBytes());
//            String signedStr = URLEncoder.encode(Base64.encodeBase64String(signData), "utf-8");
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

    public static String percentEncode(String value) throws UnsupportedEncodingException {
        return value != null ? URLEncoder.encode(value, "utf-8").replace("+", "%20")
                .replace("*", "%2A").replace("%7E", "~") : null;
    }

    public static String decryptBase64ToStdFormat(String str) {
        str = str.replace("-", "+");
        str = str.replace("_", "/");
        str = str.replace("~", "=");
        return str;
    }
}
