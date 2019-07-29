package signature.my;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author lyk
 * @Date 2019/6/13 13:43
 * @Version 1.0
 **/
@RestController
@RequestMapping("/test")
public class SignatureController {
    @Autowired
    SignatureService signatureService;

    private static Logger logger = LoggerFactory.getLogger(SignatureController.class);

    @RequestMapping(value = "/v1/signature", method = RequestMethod.GET)
    public void testSignature(HttpServletRequest request) throws UnsupportedEncodingException {
        //a~a+a.a-a_a*a=a
        signatureService.getSingatureFromRequest(request, "e76HUKfiJs7BDJM0lGnixkZw5gvMz1");
    }

    @RequestMapping(value = "/v2/signature", method = RequestMethod.GET)
    public String testSignature2(HttpServletRequest request) throws UnsupportedEncodingException {
        IamUserInfo iamUserInfoRequest;
        try {
            iamUserInfoRequest = signatureService.getIamUserInfoFromRequest(request);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

        IamUserInfo iamUserInfo = new IamUserInfo();
        iamUserInfo.setAccessKey("YA77gaiu2wItZqnwtBAcKQl12JARSe");
        iamUserInfo.setTemp(iamUserInfoRequest.getTemp());
        String signature = signatureService.calculateSignatureOfOpenAPI(iamUserInfo);
        return signature;
    }

}
