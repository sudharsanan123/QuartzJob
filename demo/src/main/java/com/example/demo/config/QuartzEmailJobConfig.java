package com.example.demo.config;

import org.quartz.JobDetail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.example.demo.job.EmailJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class QuartzEmailJobConfig {

    private static final Logger logger = LoggerFactory.getLogger(QuartzEmailJobConfig.class);

    @Bean
    public JobDetailFactoryBean emailJobDetail() {
        logger.info("Configuring EmailJob detail");
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(EmailJob.class);
        factoryBean.setDescription("Send email at regular intervals");
        factoryBean.setDurability(true);
        return factoryBean;
    }

    @Bean
    public SimpleTriggerFactoryBean emailJobTrigger(JobDetail emailJobDetail) {
        logger.info("Creating trigger for EmailJob");
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(emailJobDetail);
        factoryBean.setRepeatInterval(60000); // 60 seconds
        factoryBean.setRepeatCount(4);
        return factoryBean;
    }
}
