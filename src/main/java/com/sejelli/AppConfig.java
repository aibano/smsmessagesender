package com.sejelli;

import com.sejelli.messaging.domain.application.SmsSender;
import com.sejelli.messaging.domain.application.SmsSenderImpl;
import com.sejelli.messaging.domain.model.Setting;
import com.sejelli.messaging.services.SmsServiceProviderImpl;
import com.sejelli.messaging.services.SmsServicerProvider;
import com.sejelli.messaging.services.YamamahServiceProvider.YamamahServiceProvider;
import com.sejelli.messaging.services.repositories.SettingRepository;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

/**
 * Created by aibano on 10/16/2016.
 */
@Configuration
public class AppConfig {

    @Autowired
    private SettingRepository settingRepository;

    @Bean
    public SmsSender smsSender(){
        SmsSender senderApp = new SmsSenderImpl();
        return senderApp;
    }

    @Bean
    public SmsServicerProvider serviceProvider(){
        return new SmsServiceProviderImpl();
    }


}
