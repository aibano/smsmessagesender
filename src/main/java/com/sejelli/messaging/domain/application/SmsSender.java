package com.sejelli.messaging.domain.application;

/**
 * Created by aibano on 10/16/2016.
 */
public interface SmsSender {
    void sendPendingMessages();

    void reloadSetting();
}
