package com.velotio.dataprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.jms.annotation.EnableJms;

@EnableJms // Enable ActiveMQ support
@SpringBootApplication(exclude = {ElasticsearchDataAutoConfiguration.class})
public class DataProcessorApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataProcessorApplication.class, args);
    }
}
