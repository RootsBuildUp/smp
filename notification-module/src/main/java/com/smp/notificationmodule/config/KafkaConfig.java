package com.smp.notificationmodule.config;

import com.smp.notificationmodule.constant.AppConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConfig {

    @KafkaListener( topics = AppConstant.USER_TOPIC_NAME, groupId = AppConstant.GROUP_ID )
    public void userInfoUpdatedInfo( String massage ){
        System.out.println( massage );
    }
}
