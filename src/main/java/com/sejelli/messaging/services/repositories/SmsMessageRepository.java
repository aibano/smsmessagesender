package com.sejelli.messaging.services.repositories;

import com.sejelli.messaging.domain.model.SmsMessage;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by aibano on 10/16/2016.
 */
public interface SmsMessageRepository extends PagingAndSortingRepository<SmsMessage, Long> {
    List<SmsMessage> findBySent(byte sent);
}
