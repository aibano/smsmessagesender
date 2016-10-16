package com.sejelli.messaging.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aibano on 10/16/2016.
 */
@Entity
@Table(name = "sms_messages")
public class SmsMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String message;
    private String sender;
    private String receiver;
    private byte sent;
    private Date created;
    private Date modified;

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public SendStatus getSent() {
        return SendStatus.getType(this.sent) ;
    }

    public Date getCreated() {
        return created;
    }

    public Date getModified() {
        return modified;
    }

    public void setSent(SendStatus sent) {
        if (sent == null) {
            this.sent = 98;
        } else {
            this.sent = sent.getValue();
        }
    }
}
