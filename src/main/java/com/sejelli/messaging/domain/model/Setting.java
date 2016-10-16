package com.sejelli.messaging.domain.model;

import javax.persistence.*;

/**
 * Created by aibano on 10/16/2016.
 */
@Entity
@Table(name = "setting")
public class Setting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name="provider_id")
    private MessagingProvider provider;
    private String username;
    private String password;

    public MessagingProvider getProvider() {
        return provider;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
