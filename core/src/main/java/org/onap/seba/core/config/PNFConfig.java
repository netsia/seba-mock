package org.onap.seba.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cemturker on 01.10.2018.
 */
@Configuration
@Getter
@Setter
public class PNFConfig {
    @Value("${pnf.correlationId}")
    private String correlationId;
    @Value("${ves.url}")
    private String vesUrl;
    @Value("${pnf.macAddress}")
    private String pnfMacAddress;
    @Value("${pnf.ipv4}")
    private String pnfIpv4Address;
    @Value("${pnf.ipv6}")
    private String pnfIpv6Address;
    @Value("${pnf.kafkaConsumerTimeInterval}")
    private String kafkaConsumerTimeInterval;
    @Value("${pnf.pnfRegisterTimeInterval}")
    private String pnfRegisterTimeInterval;
    @Value("${pnf.xos.ip}")
    private String xosIp;
    @Value("${pnf.xos.port}")
    private String xosPort;
    //Access Northbound Port
    @Value("${pnf.nb.port}")
    private String nbPort;
    @Value("${pnf.username}")
    private String username;
    @Value("${pnf.password}")
    private String password;
}
