package com.sejelli.messaging.services.mobilywssp;

import java.io.UnsupportedEncodingException;

/**
 * Created by aibano on 4/19/2017.
 */
public class MobilySmsMessage {
    private String username;
    private String password;
    private String recepientNumber;
    private String sender;
    private String message;
    private static final String MOBILY_SEND_URL = "http://www.mobily.ws/api/msgSend.php";
    private static final int APPLICATION_TYPE = 64;

    public MobilySmsMessage(String username,
                            String password,
                            String recepientNumber,
                            String sender,
                            String message){
        this.username = username;
        this.password = password;
        this.recepientNumber = recepientNumber;
        this.sender = sender;
        this.message = message;
    }

    public String getUrl(){
        return MOBILY_SEND_URL;
    }

    public String getMessageBody() throws UnsupportedEncodingException {
        return String.format("mobile=%s&password=%s&numbers=%s&sender=%s&msg=%s&applicationType=%s",
                this.username,
                this.password,
                this.recepientNumber,
                this.sender,
                MobilyCharsetConverter.covert(this.message),
                APPLICATION_TYPE);
    }
}
