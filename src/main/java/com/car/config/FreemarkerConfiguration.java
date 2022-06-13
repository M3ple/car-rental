package com.car.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * freemarker configuration
 *
 * @author Gandalf
 * @since 2022/6/13
 */
@Configuration
public class FreemarkerConfiguration {

    @Resource
    protected freemarker.template.Configuration configuration;

    /**
     * 添加自定义标签
     */
    @PostConstruct
    public void setSharedVariable() {
        try {
            configuration.setSharedVariable("rootPath", "");
            configuration.setSharedVariable("staticVersion", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHH")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 自定义属性
     */
    @PostConstruct
    public void setSettings() {
        try {
            configuration.setSetting("number_format","##.##");
            configuration.setSetting("whitespace_stripping","true");
            configuration.setSetting("classic_compatible","true");
            configuration.setSetting("template_exception_handler","ignore");
            configuration.setSetting("time_format","HH:mm:ss");
            configuration.setSetting("date_format","yyyy-MM-dd");
            configuration.setSetting("locale","zh_CN");
            configuration.setSetting("output_encoding","UTF-8");
            configuration.setSetting("default_encoding","UTF-8");
            configuration.setSetting("url_escaping_charset","UTF-8");
            configuration.setSetting("datetime_format","yyyy-MM-dd HH:mm:ss");
            configuration.setSetting("template_update_delay","5");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
