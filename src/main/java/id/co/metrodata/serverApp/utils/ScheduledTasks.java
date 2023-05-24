package id.co.metrodata.serverApp.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import id.co.metrodata.serverApp.services.GradeService;

@Configuration
@EnableScheduling
public class ScheduledTasks {
    // @Autowired
    // private GradeService gradeService;

    // @Bean
    // public Executor taskExecutor() {
    // return Executors.newSingleThreadScheduledExecutor();
    // }

    // @Override
    // public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    // taskRegistrar.setScheduler(taskExecutor());
    // taskRegistrar.addTriggerTask(
    // new Runnable() {
    // @Override
    // public void run() {
    // gradeService.testScheduler();
    // }
    // },
    // new Trigger() {
    // @Override
    // public Date nextExecutionTime(TriggerContext context) {
    // withident
    // }
    // });

    // }
}
