package org.onap.seba.core;

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
    @Value("${pnf.correlationId}") private String correlationId;
    @Value("${ves.url}") private String vesUrl;
    @Value("${pnf.macAddress}") private String pnfMacAddress;
    @Value("${pnf.ipv4}") private String pnfIpv4Address;
    @Value("${pnf.ipv6}") private String getPnfIpv6Address;
}
