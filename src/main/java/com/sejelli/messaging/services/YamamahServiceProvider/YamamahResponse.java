package com.sejelli.messaging.services.YamamahServiceProvider;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by aibano on 10/16/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class YamamahResponse {
    public Object InvalidMSISDN;
    public String MessageID;
    public int Status;
    public String StatusDescription;



    public int getStatus() {
        return Status;
    }


}
