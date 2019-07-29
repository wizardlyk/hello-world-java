package signature.my;

import java.util.Map;

/**
 * @author lyk
 * @Date 2019/6/13 14:51
 * @Version 1.0
 **/
public class IamUserInfo {
    private String accessKey;
    private String accessSecret;
    private String userId;
    private String projectId;
    private String projectName;
    private String createAt;
    private String expiredAt;
    private int enabled;
    private String type;
    private String signature;
    /**
     * 用来计算的参数组成的map
     */
    private Map temp;

    public IamUserInfo() {
    }

    public IamUserInfo(String accessKey, String accessSecret, String userId, String projectId, String projectName, String createAt, String expiredAt, int enabled, String type, String signature, Map temp) {
        this.accessKey = accessKey;
        this.accessSecret = accessSecret;
        this.userId = userId;
        this.projectId = projectId;
        this.projectName = projectName;
        this.createAt = createAt;
        this.expiredAt = expiredAt;
        this.enabled = enabled;
        this.type = type;
        this.signature = signature;
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "IamUserInfo{" +
                "accessKey='" + accessKey + '\'' +
                ", accessSecret='" + accessSecret + '\'' +
                ", userId='" + userId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", createAt='" + createAt + '\'' +
                ", expiredAt='" + expiredAt + '\'' +
                ", enabled=" + enabled +
                ", type='" + type + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getAccessSecret() {
        return accessSecret;
    }

    public void setAccessSecret(String accessSecret) {
        this.accessSecret = accessSecret;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(String expiredAt) {
        this.expiredAt = expiredAt;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Map getTemp() {
        return temp;
    }

    public void setTemp(Map temp) {
        this.temp = temp;
    }
}
