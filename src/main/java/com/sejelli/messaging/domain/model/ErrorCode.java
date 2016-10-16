package com.sejelli.messaging.domain.model;

/**
 * Created by aibano on 10/16/2016.
 */
public enum ErrorCode {
    UNKOWN(0),
    NONE(1),
    NO_FUNDS(2),
    NOT_ENOUGH_FUNDS(3),
    UNKNOWN_MOBILE_NUMBER(4),
    WRONG_PASSWORD(5),
    SERVICE_UNAVAILABLE(6),
    SENDER_NAME_REJECTED(13),
    UNKOWN_SENDER_NAME(14),
    WRONG_RECIEVER_NUMBER(15),
    EMPTY_SENDER_NAME(16),
    WRONG_UNICODE(17),
    INVALID_FORMAT(110);


    private final int code;
    ErrorCode(int code) { this.code = code; }
    public int getValue() { return code; }
}
