package io.anura.sdk;

import java.util.HashMap;

/**
 * Builder class for creating a {@link DirectRequest}
 */
public class DirectRequestBuilder {
    private String source;
    private String campaign;
    private String ipAddress;
    private String userAgent;
    private String app;
    private String device;
    private HashMap<Integer, String> additionalData;

    public DirectRequestBuilder() {
        this.source = "";
        this.campaign = "";
        this.ipAddress = "";
        this.userAgent = "";
        this.app = "";
        this.device = "";
        this.additionalData = new HashMap<>();
    }

    /**
     * Builds and returns a {@link DirectRequest}
     *
     * @return  the DirectRequest that was configured and built
     */
    public DirectRequest build() {
        return new DirectRequest(
                this.source,
                this.campaign,
                this.ipAddress,
                this.userAgent,
                this.app,
                this.device,
                this.additionalData
        );
    }

    /**
     * Sets this {@link DirectRequest}'s source.
     * A source is a variable, declared by you, to identify "source" traffic within Anura's dashboard interface.
     *
     * @param source        the source to set
     * @return              this builder
     */
    public DirectRequestBuilder setSource(String source) {
        this.source = source;
        return this;
    }

    /**
     * Sets this {@link DirectRequest}'s campaign.
     * A campaign is a subset variable of "source", declared by you, to identify "campaign" traffic within Anura's dashboard interface.
     *
     * @param campaign      the campaign to set
     * @return              this builder
     */
    public DirectRequestBuilder setCampaign(String campaign) {
        this.campaign = campaign;
        return this;
    }

    /**
     * Sets this {@link DirectRequest}'s IP address. Necessary for all requests used by {@link AnuraDirect}.
     * Both IPv4 and IPv6 addresses are supported.
     *
     * @param ipAddress     the IP address to set
     * @return              this builder
     */
    public DirectRequestBuilder setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    /**
     * Sets this {@link DirectRequest}'s user agent.
     * Providing a user agent increases the accuracy of results.
     *
     * @param userAgent     the user agent of your visitor
     * @return              this builder
     */
    public DirectRequestBuilder setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    /**
     * Sets this {@link DirectRequest}'s application package identifier.
     * While not required, it's highly encouraged to supply an application package identifier when available
     * as it will allow Anura Direct to assess the visitor more accurately.
     * This ID may also be referred as app ID, bundle ID, package name, etc.
     *
     * @param app the application package identifier of your visitor
     * @return      this builder
     */
    public DirectRequestBuilder setApp(String app) {
        this.app = app;
        return this;
    }

    /**
     * Sets this {@link DirectRequest}'s device identifier.
     * While not required, it's highly encouraged to supply a device identifier when available
     * as it will allow Anura Direct to assess the visitor more accurately.
     *
     * @param device  the device identifier of your visitor
     * @return          this builder
     */
    public DirectRequestBuilder setDevice(String device) {
        this.device = device;
        return this;
    }

    /**
     * Sets this {@link DirectRequest}'s additional data.
     * Additional Data gives you the ability to pass in select points of data with your getResult() calls from {@link AnuraDirect},
     * essentially turning Anura into "your database for transactional data".
     *
     * @param additionalData    the additional data you would like to send
     * @return                  this builder
     */
    public DirectRequestBuilder setAdditionalData(HashMap<Integer, String> additionalData) {
        this.additionalData = additionalData;
        return this;
    }
}
