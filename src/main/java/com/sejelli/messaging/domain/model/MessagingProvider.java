package com.sejelli.messaging.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by aibano on 10/16/2016.
 */
@Entity
@Table(name = "providers")
public class MessagingProvider {
    @Id
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
