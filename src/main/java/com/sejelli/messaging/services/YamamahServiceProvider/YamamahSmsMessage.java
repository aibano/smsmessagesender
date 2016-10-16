package com.sejelli.messaging.services.YamamahServiceProvider;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sejelli.messaging.domain.model.SmsMessage;

/**
 * Created by aibano on 10/16/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class YamamahSmsMessage {
    public String Username;
    public String Password;
    public String Tagname;
    public String RecepientNumber;
    public String Message;
    public int SendDateTime = 0;

    public YamamahSmsMessage(){}

    public YamamahSmsMessage(String username,
                             String password,
                             SmsMessage message){
        this.Username = username;
        this.Password = password;
        this.Tagname = message.getSender();
        this.RecepientNumber = message.getReceiver();
        this.Message = message.getMessage();
    }

}
