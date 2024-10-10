package io.anura.sdk;

import java.util.HashMap;

public class DirectRequestBuilder {
    private String instanceId;
    private String source;
    private String campaign;
    private String ipAddress;
    private String userAgent;
    private String appId;
    private String deviceId;
    private HashMap<String, String> additionalData;

    public DirectRequestBuilder() {
        this.instanceId = "";
        this.source = "";
        this.campaign = "";
        this.ipAddress = "";
        this.userAgent = "";
        this.appId = "";
        this.deviceId = "";
        this.additionalData = new HashMap<>();
    }

    public DirectRequest build() {
        return new DirectRequest(
                this.instanceId,
                this.source,
                this.campaign,
                this.ipAddress,
                this.userAgent,
                this.appId,
                this.deviceId,
                this.additionalData
        );
    }

    public DirectRequestBuilder setInstanceId(String instanceId) {
        this.instanceId = instanceId;
        return this;
    }

    public DirectRequestBuilder setSource(String source) {
        this.source = source;
        return this;
    }

    public DirectRequestBuilder setCampaign(String campaign) {
        this.campaign = campaign;
        return this;
    }

    public DirectRequestBuilder setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public DirectRequestBuilder setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public DirectRequestBuilder setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public DirectRequestBuilder setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public DirectRequestBuilder setAdditionalData(HashMap<String, String> additionalData) {
        this.additionalData = additionalData;
        return this;
    }
}
