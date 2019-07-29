package signature.my;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public interface SignatureService {
    /**
     * v2
     * @param iamUserInfo
     * @return
     * @throws UnsupportedEncodingException
     */
    String calculateSignatureOfOpenAPI(IamUserInfo iamUserInfo) throws UnsupportedEncodingException;
    IamUserInfo getIamUserInfoFromRequest(HttpServletRequest request) throws Exception;

    /**
     * v1
     * @param request
     * @param accessSecret
     * @throws UnsupportedEncodingException
     */
    void getSingatureFromRequest(HttpServletRequest request, String accessSecret) throws UnsupportedEncodingException;
}
