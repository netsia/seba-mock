package org.onap.seba.core;

import lombok.extern.slf4j.Slf4j;
import org.onap.seba.aai.AaiClient;
import org.onap.seba.core.config.PNFConfig;
import org.onap.seba.model.PNF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by cemturker on 01.10.2018.
 */
@Service
@Slf4j
public class MockPNFEntryCreationService {

    private PNFConfig pnfConfig;
    private AaiClient aaiClient;

    @Autowired
    public MockPNFEntryCreationService(PNFConfig pnfConfig, AaiClient aaiClient) {
        this.pnfConfig = pnfConfig;
        this.aaiClient = aaiClient;
    }

    @PostConstruct
    public void postConstruct(){
        PNF pnf = new PNF();
        pnf.setPnfId(pnfConfig.getCorrelationId());
        pnf.setPnfName(pnfConfig.getCorrelationId());
        try {
            aaiClient.putPnf(pnf);
            log.info("PNF is created with {}",pnf);
        }catch (Exception e) {
            log.error("",e);
        }

    }
}
