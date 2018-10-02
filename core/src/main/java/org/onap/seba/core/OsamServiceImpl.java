package org.onap.seba.core;

import lombok.extern.slf4j.Slf4j;
import org.onap.seba.core.config.OsamConfig;
import org.onap.seba.model.AccessPod;
import org.onap.seba.service.OsamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cemturker on 02.10.2018.
 */
@Service
@Slf4j
public class OsamServiceImpl implements OsamService {

    private OsamConfig osamConfig;

    @Autowired
    public OsamServiceImpl(OsamConfig osamConfig) {
        this.osamConfig = osamConfig;
    }

    @Override
    public void register(AccessPod accessPod) {
        OsamWebClient.client(osamConfig).withRegister(accessPod);
        log.info("{} is successfully registered",accessPod);
    }

    @Override
    public void deRegister(String pnfId) {
        OsamWebClient.client(osamConfig).withRemove(pnfId);
        log.info("Id:{} is successfully removed",pnfId);
    }
}
