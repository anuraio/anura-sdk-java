package io.anura.sdk;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Represents a visitor assessment result that is returned from the Anura Direct API.
 */
public class DirectResult {
    private final String result;
    private final Integer mobile;

    @SerializedName("rule_sets")
    private final String[] ruleSets;

    @SerializedName("invalid_traffic_type")
    private final String invalidTrafficType;

    public DirectResult(String result, Integer mobile, String[] ruleSets, String invalidTrafficType) {
        this.result = result;
        this.mobile = mobile;
        this.ruleSets = ruleSets;
        this.invalidTrafficType = invalidTrafficType;
    }

    /**
     * Returns whether the visitor is deemed to be suspect.
     *
     * @return  whether the visitor is suspect
     */
    public boolean isSuspect() {
        return this.result.equals("suspect");
    }

    /**
     * Returns whether the visitor is deemed to be non-suspect.
     *
     * @return  whether the visitor is non-suspect
     */
    public boolean isNonSuspect() {
        return this.result.equals("non-suspect");
    }

    public String getResult() {
        return this.result;
    }

    /**
     * Returns whether the visitor is deemed to be from a mobile device.
     *
     * @return  whether the visitor was from a mobile device
     */
    public Boolean isMobile() {
        return (this.mobile == null) ? null : (this.mobile != 0);
    }

    /**
     * Returns which rule set(s) indicated that a visitor should be deemed suspect.
     * Returning rule sets requires "return rule sets" to be enabled. You can reach out to support to have Rule Sets returned.
     *
     * @see <a href="https://docs.anura.io/integration/direct">Anura Direct Documentation</a> to learn more about adding this feature
     * @return  Rule Sets that indicated a visitor should be deemed suspect.
     *          Will return an empty array on non-suspect results,
     *          and will return null for all results if you have "return rule sets" disabled
     */
    public String[] getRuleSets() {
        return this.ruleSets;
    }

    /**
     * Returns which invalid traffic type indicated that a visitor should be deemed suspect.
     * Returning invalid traffic type requires "return invalid traffic type" to be enabled. You can reach out to support to have Rule Sets returned.
     *
     * @see <a href="https://docs.anura.io/definitions/invalid-traffic-types">Traffic Type Definitions</a> to learn more about invalid traffic types
     * @see <a href="https://docs.anura.io/integration/direct">Anura Direct Documentation</a> to learn more about adding this feature
     *
     * @return  Invalid traffic type that indicated a visitor should be deemed suspect.
     *          Returns null on non-suspect results and/or if you have "return invalid traffic type" disabled
     */
    public String getInvalidTrafficType() {
        return this.invalidTrafficType;
    }

    @Override
    public String toString() {
        return "DirectResult{" +
                "result='" + result + '\'' +
                ", mobile=" + mobile +
                ", ruleSets=" + Arrays.toString(ruleSets) +
                ", invalidTrafficType='" + invalidTrafficType + '\'' +
                '}';
    }
}
