package io.anura.sdk;

import java.util.HashMap;

/**
 * A POJO that represents an API request to be sent to the Anura Direct API.
 * It is used by {@link  AnuraDirect}
 */
public class DirectRequest {
    private String source;
    private String campaign;
    private String ipAddress;
    private String userAgent;
    private String app;
    private String device;
    private HashMap<Integer, String> additionalData;

    public DirectRequest(
            String source,
            String campaign,
            String ipAddress,
            String userAgent,
            String app,
            String device,
            HashMap<Integer, String> additionalData
    ) {
        this.source = source;
        this.campaign = campaign;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.app = app;
        this.device = device;
        this.additionalData = additionalData;
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

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public HashMap<Integer, String> getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(HashMap<Integer, String> additionalData) {
        this.additionalData = additionalData;
    }
}
