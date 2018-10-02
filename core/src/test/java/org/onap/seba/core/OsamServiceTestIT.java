package org.onap.seba.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.onap.seba.core.config.OsamConfig;
import org.onap.seba.core.config.PNFConfig;
import org.onap.seba.model.AccessPod;
import org.onap.seba.service.OsamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by cemturker on 02.10.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {OsamServiceImpl.class, OsamConfig.class, PNFConfig.class})
@TestPropertySource(locations = {"classpath:application.properties",
        "classpath:osam.properties",
        "classpath:aai.properties"})
public class OsamServiceTestIT {

    @Autowired
    private OsamService osamService;

    @Autowired
    private PNFConfig pnfConfig;

    @Test
    public void requestOsamCore(){
        AccessPod accessPod = new AccessPod();
        accessPod.setPnfId(pnfConfig.getCorrelationId());
        accessPod.setCoreIp(pnfConfig.getXosIp());
        accessPod.setCorePort(pnfConfig.getXosPort());
        accessPod.setPort(pnfConfig.getNbPort());
        accessPod.setIp(pnfConfig.getPnfIpv4Address());
        accessPod.setUsername(pnfConfig.getUsername());
        accessPod.setPassword(pnfConfig.getPassword());
        System.out.println(accessPod);

        try {
            osamService.register(accessPod);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
