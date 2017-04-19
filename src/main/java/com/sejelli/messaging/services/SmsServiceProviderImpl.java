package com.sejelli.messaging.services;

import com.sejelli.messaging.domain.model.ErrorCode;
import com.sejelli.messaging.domain.model.Setting;
import com.sejelli.messaging.domain.model.SmsMessage;
import com.sejelli.messaging.services.YamamahServiceProvider.YamamahServiceProvider;
import com.sejelli.messaging.services.mobilywssp.MobilyServiceProvider;
import com.sejelli.messaging.services.mqamsmsserviceprovider.MqamsmsServiceProvider;
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
        else if(setting.getProvider().getId() == 2) // mobily.com
            this.servicerProvider = new MobilyServiceProvider(setting.getUsername(),
                    setting.getPassword());
        /*else if(setting.getProvider().getId() == 3) // mqamsms.com
            this.servicerProvider = new MqamsmsServiceProvider(setting.getUsername(),
                    setting.getPassword());*/
        else
            throw new RuntimeException(String.format("Undefined Settings with id %s", setting.getProvider().getId()));

        return this;
    }
}
