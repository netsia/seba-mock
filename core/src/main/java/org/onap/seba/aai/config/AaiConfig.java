package org.onap.seba.aai.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by cemturker on 12.04.2018.
 */
@Configuration
@PropertySource("classpath:aai.properties")
@Slf4j
@Getter
@Setter
public class AaiConfig {
    private static final String AAI_HOST = "${aai.host}";
    private static final String AAI_PORT = "${aai.port}";

    private static final String AAI_PROTOCOL = "${aai.protocol}";

    private static final String AAI_USERNAME = "${aai.username}";
    private static final String AAI_PASSWORD = "${aai.password}";
    private static final String AAI_BASE_PATH = "${aai.base.path}";
    private static final String AAI_PNF_PATH = "${aai.pnf.path}";

    @Value(AAI_HOST)
    private String host;
    @Value(AAI_PORT)
    private int port;
    @Value(AAI_PROTOCOL)
    private String protocol;
    @Value(AAI_USERNAME)
    private String username;
    @Value(AAI_PASSWORD)
    private String password;
    @Value(AAI_BASE_PATH)
    private String basePath;
    @Value(AAI_PNF_PATH)
    private String pnfPath;

}
