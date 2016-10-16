package com.sejelli.messaging.domain.model;

/**
 * Created by aibano on 10/16/2016.
 */
public enum SendStatus {
    PENDING((byte)0), SENT((byte)1), FAILED((byte)2);

    private final byte code;
    SendStatus(byte code) { this.code = code; }
    public byte getValue() { return code; }

    public static SendStatus getType(byte code) {


        for (SendStatus position : SendStatus.values()) {
            if (code == position.getValue()) {
                return position;
            }
        }
        throw new IllegalArgumentException("No matching type for id " + code);
    }
}
