package com.car.config;

import com.car.utils.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Global Configuration
 *
 * @author Gandalf
 * @since 2022-06-11 18:51
 */
@Configuration
public class GlobalConfiguration {

    @Bean("idWorker")
    public IdWorker idWorker() {
        return new IdWorker(1,1,1);
    }

    @Bean("carRentalScheduler")
    public ScheduledExecutorService executorService() {
        return Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors() * 2 + 1);
    }
}
