package com.ecaicn.hms.webapp.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.ecaicn.hms.quartz.InvokingJobDetailDetailFactory;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author cheng_mboy
 * @create 2017-07-05-20:35
 */
@Configuration
public class QuartzConfig {

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(
            @Qualifier("updateHomeworkJobTrigger") Trigger updateHomeworkJobTrigger,
            @Qualifier("updateHomeworkScheduleJobTrigger") Trigger updateHomeworkScheduleJobTrigger,
            @Qualifier("autoCommitJobTrigger") Trigger autoCommitJobTrigger,
            @Value("${job.quartz.properties-file}") String propsFile
    ) throws IOException, SchedulerException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        factory.setStartupDelay(10);
        factory.setQuartzProperties(quartzProperties(propsFile));
        factory.setApplicationContextSchedulerContextKey("applicationContext");
        factory.setAutoStartup(true);
        factory.setTriggers(
                updateHomeworkJobTrigger,
                updateHomeworkScheduleJobTrigger,
                autoCommitJobTrigger
        );
        return factory;
    }


    @Bean
    public JobDetailFactoryBean updateHomeworkStatusJobDetail() {
        return createJobDetail(InvokingJobDetailDetailFactory.class, "updateHomework", "updateHomeworkStatusJob");
    }

    @Bean(name = "updateHomeworkJobTrigger")
    public CronTriggerFactoryBean updateHomeworkJobTrigger(
            @Qualifier("updateHomeworkStatusJobDetail") JobDetail jobDetail
    ) {
        return dialogStatusTrigger(jobDetail, "0 */1 * * * ? *");
    }

    @Bean(name = "updateHomeworkScheduleJobDetail")
    public JobDetailFactoryBean updateHomeworkScheduleJobDetail() {
        return createJobDetail(InvokingJobDetailDetailFactory.class, "updateHomework", "updateHomeworkScheduleJob");
    }

    @Bean(name = "updateHomeworkScheduleJobTrigger")
    public CronTriggerFactoryBean updateHomeworkScheduleJobTrigger(
            @Qualifier("updateHomeworkScheduleJobDetail") JobDetail jobDetail
    ) {
        return dialogStatusTrigger(jobDetail, "10 0 4 * * ? *");
    }

    @Bean(name = "autoCommitJobDetail")
    public JobDetailFactoryBean autoCommitJobDetail() {
        return createJobDetail(InvokingJobDetailDetailFactory.class, "updateHomework", "autoCommitJob");
    }

    @Bean(name = "autoCommitJobTrigger")
    public CronTriggerFactoryBean autoCommitJobTrigger(
            @Qualifier("autoCommitJobDetail") JobDetail jobDetail
    ) {
        return dialogStatusTrigger(jobDetail, "5 */1 * * * ? *");
    }


    private static JobDetailFactoryBean createJobDetail(Class<?> jobClass, String groupName, String targetObject) {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(jobClass);
        factoryBean.setDurability(true);
        factoryBean.setRequestsRecovery(true);
        factoryBean.setGroup(groupName);
        Map<String, String> map = new HashMap<>();
        map.put("targetObject", targetObject);
        map.put("targetMethod", "execute");
        factoryBean.setJobDataAsMap(map);
        return factoryBean;
    }

    private static CronTriggerFactoryBean dialogStatusTrigger(JobDetail jobDetail, String cronExpression) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setCronExpression(cronExpression);
        return factoryBean;
    }

    private Properties quartzProperties(String propsFile) throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource(propsFile));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }
}
