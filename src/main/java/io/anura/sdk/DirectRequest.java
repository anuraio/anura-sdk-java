package io.anura.sdk;

import java.util.HashMap;

/**
 * A POJO that represents an API request to be sent to the Anura Direct API.
 * It is used by {@link  AnuraDirect}
 */
public class DirectRequest {
    private String instanceId;
    private String source;
    private String campaign;
    private String ipAddress;
    private String userAgent;
    private String appId;
    private String deviceId;
    private HashMap<String, String> additionalData;

    public DirectRequest(
            String instanceId,
            String source,
            String campaign,
            String ipAddress,
            String userAgent,
            String appId,
            String deviceId,
            HashMap<String, String> additionalData
    ) {
        this.instanceId = instanceId;
        this.source = source;
        this.campaign = campaign;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.appId = appId;
        this.deviceId = deviceId;
        this.additionalData = additionalData;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public HashMap<String, String> getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(HashMap<String, String> additionalData) {
        this.additionalData = additionalData;
    }
}
