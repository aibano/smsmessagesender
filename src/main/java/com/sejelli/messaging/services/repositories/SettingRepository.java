package com.sejelli.messaging.services.repositories;

import com.sejelli.messaging.domain.model.Setting;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by aibano on 10/16/2016.
 */
public interface SettingRepository extends PagingAndSortingRepository<Setting, Long> {
}
