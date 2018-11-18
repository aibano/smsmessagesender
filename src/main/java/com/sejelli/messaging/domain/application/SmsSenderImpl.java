package com.sejelli.messaging.domain.application;

import com.sejelli.messaging.domain.model.ErrorCode;
import com.sejelli.messaging.domain.model.SendStatus;
import com.sejelli.messaging.domain.model.Setting;
import com.sejelli.messaging.domain.model.SmsMessage;
import com.sejelli.messaging.services.SmsServicerProvider;
import com.sejelli.messaging.services.YamamahServiceProvider.YamamahServiceProvider;
import com.sejelli.messaging.services.repositories.SettingRepository;
import com.sejelli.messaging.services.repositories.SmsMessageRepository;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Set;

/**
 * Created by aibano on 10/16/2016.
 */
public class SmsSenderImpl implements SmsSender {
    @Autowired
    SmsMessageRepository smsMessageRepository;

    @Autowired
    SettingRepository settingRepository;

    @Autowired
    SmsServicerProvider smsServicerProvider;

    private Setting latestSettings;

    private static final Logger log = LoggerFactory.getLogger(SmsSenderImpl.class);

    @Override
    public void sendPendingMessages() {
        if(this.latestSettings == null){
            log.warn("There is no SMS service provider defined... ignoring");
            return;
        }

        if(this.latestSettings.getUsername() == null || this.latestSettings.getUsername().isEmpty()) {
            log.warn("Username cannot be empty");
            return;
        }


        byte unsent = 0;
        List<SmsMessage> pendingMessages = this.smsMessageRepository.findBySent(unsent);
        log.info("Found {} unsent message", pendingMessages.size());
        for (SmsMessage messageRecord:
             pendingMessages) {
            // Send SMS Message
            ErrorCode errorCode = this.smsServicerProvider.send(messageRecord);

            if (errorCode == ErrorCode.NONE) {
                messageRecord.setSent(SendStatus.SENT);
                log.info("Message with ID: {}, has been sent", messageRecord.getId());
            }
            else{
                messageRecord.setSent(SendStatus.FAILED);
                messageRecord.setError(errorCode.name());
                log.warn("Message with ID: {}, failed to sent with error code: {}", messageRecord.getId(),
                        errorCode.name());
            }

            // Update DB Record
            this.smsMessageRepository.save(messageRecord);

        }

    }

    @Override
    public void reloadSetting() {
        SmsServicerProvider smsServicerProvider = null;
        Iterable<Setting> settings = this.settingRepository.findAll();
        boolean moreThanOne = false;
        for (Setting settingRecord :
                settings) {
            if (moreThanOne)
                throw new IndexOutOfBoundsException("Cannot have more than one setting");


            moreThanOne = true;

            this.latestSettings = settingRecord;
            try {
                this.smsServicerProvider.setSetting(settingRecord);
            } catch (Exception ex){
                log.error("Exception Occured While reloading the settings:" + ex.getMessage());
                this.latestSettings = null;
            }
        }
        // In case no settings has been found
        if(moreThanOne == false)
            log.warn("There is no settings defined");
    }
}
