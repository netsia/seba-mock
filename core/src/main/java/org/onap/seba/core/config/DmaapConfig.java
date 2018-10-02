package org.onap.seba.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by cemturker on 01.10.2018.
 */
@Configuration
@Getter
@Setter
@PropertySource("classpath:dmaap.properties")
public class DmaapConfig {
    @Value("${dmaap.uri}")
    private String dmaapUri;
    @Value("${dmaap.consumer.group}")
    private String consumerGroup;
    @Value("${dmaap.consumer.id}")
    private String consumerId;
    @Value("${dmaap.consumer.time.interval}")
    private String timeInterval;
}
