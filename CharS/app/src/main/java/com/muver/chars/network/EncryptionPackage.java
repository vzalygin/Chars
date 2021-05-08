package com.muver.chars.network;

import com.muver.chars.util.EncodingType;
import com.muver.chars.util.OperationState;
import com.muver.chars.util.OperationType;

public class EncryptionPackage {

    private OperationState state;
    private String container;
    private String secret;
    private String key;
    private String result;
    private EncodingType encType;
    private OperationType opType;

    public EncryptionPackage(String container, String secret, String key, EncodingType encType, OperationType opType) {
        this.container = container;
        this.secret = secret;
        this.key = key;
        this.encType = encType;
        this.opType = opType;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public EncodingType getEncType() {
        return encType;
    }

    public void setEncType(EncodingType encType) {
        this.encType = encType;
    }

    public OperationType getOpType() {
        return opType;
    }

    public void setOpType(OperationType opType) {
        this.opType = opType;
    }

    public OperationState getState() {
        return state;
    }

    public void setState(OperationState state) {
        this.state = state;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
