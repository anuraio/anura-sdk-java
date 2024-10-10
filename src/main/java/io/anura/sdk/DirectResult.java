package io.anura.sdk;

import java.util.Arrays;
import java.util.Optional;

public class DirectResult {
    private final String result;
    private final Integer mobile;
    private final String[] ruleSets;
    private final String invalidTrafficType;

    public DirectResult(String result, Integer mobile, String[] ruleSets, String invalidTrafficType) {
        this.result = result;
        this.mobile = mobile;
        this.ruleSets = ruleSets;
        this.invalidTrafficType = invalidTrafficType;
    }

    public boolean isSuspect() {
        return this.result.equals("suspect");
    }

    public boolean isNonSuspect() {
        return this.result.equals("non-suspect");
    }

    public String getResult() {
        return this.result;
    }

    public Boolean isMobile() {
        return (this.mobile == null) ? null : (this.mobile != 0);
    }

    public String[] getRuleSets() {
        return this.ruleSets;
    }

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
