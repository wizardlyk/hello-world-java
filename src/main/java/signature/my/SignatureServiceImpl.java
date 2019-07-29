package signature.my;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class SignatureServiceImpl implements SignatureService {

    private static Logger logger = LoggerFactory.getLogger(SignatureServiceImpl.class);

    /**
     * v2
     *
     * @param iamUserInfo
     * @return
     * @throws UnsupportedEncodingException
     */
    @Override
    public String calculateSignatureOfOpenAPI(IamUserInfo iamUserInfo) throws UnsupportedEncodingException {
        Map temp = iamUserInfo.getTemp();
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
        logger.info("canonicalizedQueryString: {}", canonicalizedQueryString);
        String stringToSign = "GET" + "&" + percentEncode("/") + "&" + percentEncode(canonicalizedQueryString);
        logger.info("StringToSign: {}", stringToSign);

        //Base64 encode
        Mac mac;
        try {
            SecretKeySpec secret_key = new SecretKeySpec((iamUserInfo.getAccessSecret() + "&").getBytes(), "HmacSHA1");
            mac = Mac.getInstance(secret_key.getAlgorithm());
            mac.init(secret_key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("NoSuchAlgorithmException");
            return null;
        } catch (InvalidKeyException e) {
            logger.error("InvalidKeyException");
            e.printStackTrace();
            return null;
        }
        byte[] signData = mac.doFinal(stringToSign.getBytes());
        String signedString = percentEncode(Base64.encodeBase64String(signData));
        logger.info("signedString: {}", signedString);
        return signedString;
    }

    @Override
    public IamUserInfo getIamUserInfoFromRequest(HttpServletRequest request) throws Exception {
        IamUserInfo iamUserInfo = new IamUserInfo();
        String queryString = "";
        Map<String, String[]> map = request.getParameterMap();
        if (map != null && map.size() != 0) {
            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                queryString += entry.getKey() + "=";
                for (int i = 0; i < entry.getValue().length; i++) {
                    queryString += entry.getValue()[i];
                }
                queryString += "&";
            }
            queryString = queryString.substring(0, queryString.length() - 1);
            logger.info("url: {}", request.getRequestURL());
            logger.info("queryString: {}", queryString);

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
                logger.error("Signature or AccessKeyId is null");
                throw new Exception("Signature or AccessKeyId is null");
            }
            logger.info("Signature: {} AccessKeyId: {}", signature, accessKeyId);
            iamUserInfo.setAccessKey(accessKeyId);
            iamUserInfo.setSignature(signature);
            iamUserInfo.setTemp(temp);
            return iamUserInfo;
        }
        logger.error("queryString is null");
        return null;
    }


    /**
     * v1
     *
     * @param request
     * @param accessSecret
     * @throws UnsupportedEncodingException
     */
    @Override
    public void getSingatureFromRequest(HttpServletRequest request, String accessSecret) throws UnsupportedEncodingException {
        String queryString = "";
        Map<String, String[]> map = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            queryString += entry.getKey() + "=";
            for (int i = 0; i < entry.getValue().length; i++) {
                queryString += entry.getValue()[i];
            }
            queryString += "&";
        }
        queryString = queryString.substring(0, queryString.length() - 1);
        System.out.println("queryString: " + queryString);
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
            System.out.println("canonicalizedQueryString" + canonicalizedQueryString);
            String stringToSign = "GET" + "&" + percentEncode("/") + "&" + percentEncode(canonicalizedQueryString);
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

    private String percentEncode(String value) throws UnsupportedEncodingException {
        return value != null ? URLEncoder.encode(value, "utf-8").replace("+", "%20")
                .replace("*", "%2A").replace("%7E", "~") : null;
    }
}
