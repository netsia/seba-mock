package org.onap.seba.core.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by cemturker on 02.10.2018.
 */
@Configuration
@PropertySource("classpath:osam.properties")
@Getter
@Setter
@ToString
public class OsamConfig {
    @Value("${osam.ip}")
    private String ip;
    @Value("${osam.port}")
    private String port;
}
