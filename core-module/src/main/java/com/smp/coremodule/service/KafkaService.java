package com.smp.coremodule.service;

import com.smp.coremodule.constant.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void userInfoNotify( String massage ){
        kafkaTemplate.send( AppConstant.USER_TOPIC_NAME, massage );
    }

}
