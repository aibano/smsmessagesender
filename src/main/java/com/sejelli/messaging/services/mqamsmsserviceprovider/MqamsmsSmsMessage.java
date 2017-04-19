package com.sejelli.messaging.services.mqamsmsserviceprovider;

import com.sejelli.messaging.domain.model.SmsMessage;

/**
 * Created by aibano on 2/5/2017.
 */
public class MqamsmsSmsMessage {
    private String username;
    private String password;
    private String sender;
    private String recepientNumber;
    private String message;
    private int SendDateTime = 0;
    private static final String URL = "http://mqamsms.com/smspro/sendsms.php?" +
            "user={username}" +
            "&password={password}" +
            "&numbers={recepient}" +
            "&sender={sender}" +
            "&message={message}" +
            "&lang=ar";

    public MqamsmsSmsMessage(){}

    public MqamsmsSmsMessage(String username,
                             String password,
                             SmsMessage message){
        this.setUsername(username);
        this.setPassword(password);
        this.setSender(message.getSender());
        this.setRecepientNumber(message.getReceiver());
        this.setMessage(message.getMessage());
    }

    public String getGetUrl(){
        return this.URL.replace("{username}", this.username)
                .replace("{password}", this.password)
                .replace("{recepient}", this.recepientNumber)
                .replace("{sender}", this.sender)
                .replace("message", this.message);

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecepientNumber() {
        return recepientNumber;
    }

    public void setRecepientNumber(String recepientNumber) {
        this.recepientNumber = recepientNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSendDateTime() {
        return SendDateTime;
    }

    public void setSendDateTime(int sendDateTime) {
        SendDateTime = sendDateTime;
    }
}
