package com.sejelli.messaging.services;

import com.sejelli.messaging.domain.model.ErrorCode;
import com.sejelli.messaging.domain.model.Setting;
import com.sejelli.messaging.domain.model.SmsMessage;
import com.sejelli.messaging.services.YamamahServiceProvider.YamamahServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by aibano on 10/16/2016.
 */
public class SmsServiceProviderImpl implements SmsServicerProvider {

    private SmsServicerProvider servicerProvider;
    private Setting setting;

    @Override
    public ErrorCode send(SmsMessage smsMessage) {


        return this.servicerProvider.send(smsMessage);
    }

    @Override
    public SmsServicerProvider setSetting(Setting setting) {
        this.setting = setting;
        if(setting.getProvider().getId() == 1) // YAMAMAH
            this.servicerProvider = new YamamahServiceProvider(setting.getUsername(),
                    setting.getPassword());
        else
            throw new RuntimeException(String.format("Undefined Settings with id %s", setting.getProvider().getId()));

        return this;
    }
}
