package com.sejelli.messaging.services;

import com.sejelli.messaging.domain.model.ErrorCode;
import com.sejelli.messaging.domain.model.Setting;
import com.sejelli.messaging.domain.model.SmsMessage;

/**
 * Created by aibano on 10/16/2016.
 */
public interface SmsServicerProvider {
    ErrorCode send(SmsMessage smsMessage);

    SmsServicerProvider setSetting(Setting setting);
}
