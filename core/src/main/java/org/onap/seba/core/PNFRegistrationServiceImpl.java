package org.onap.seba.core;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.onap.seba.common.exception.HTTPException;
import org.onap.seba.common.exception.NullParameterException;
import org.onap.seba.common.util.ModelUtils;
import org.onap.seba.common.util.RequestSender;
import org.onap.seba.model.CommonEventHeader;
import org.onap.seba.model.Event;
import org.onap.seba.model.PnfRegistrationFields;
import org.onap.seba.service.PNFRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class PNFRegistrationServiceImpl implements PNFRegistrationService {

    private ScheduledExecutorService collectorExecuter = Executors.newScheduledThreadPool(1);

    private PNFConfig pnfConfig;

    @Autowired
    public PNFRegistrationServiceImpl(PNFConfig pnfConfig) {
        this.pnfConfig = pnfConfig;
    }

    @PostConstruct
    public void before()
    {
        startRegistration();
    }

    @Override
    public void startRegistration() {
        Runnable collectTask = new Runnable() {
            @Override
            public void run() {
                try {
                    registerPNF();
                } catch (NullParameterException e) {
                    log.error("null param");
                } catch (HTTPException e) {
                    log.error("http exep");

                } catch (URISyntaxException e) {
                    log.error("uri syntax");

                }
            }
        };


        collectorExecuter.scheduleWithFixedDelay(collectTask,0,1, TimeUnit.SECONDS);

    }

    private void registerPNF() throws NullParameterException, HTTPException, URISyntaxException {
        CommonEventHeader commonEventHeader = ModelUtils.getDefaultPnfCommontEventHeader(pnfConfig.getCorrelationId());
        PnfRegistrationFields pnfRegistrationFields = ModelUtils.createPnfRegistration(
                pnfConfig.getPnfMacAddress(), pnfConfig.getPnfIpv4Address(), pnfConfig.getGetPnfIpv6Address());
        Event event = new Event(commonEventHeader,pnfRegistrationFields);
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type","application/json");
        ResponseEntity<String> responseEntity = RequestSender.sendRequest(pnfConfig.getVesUrl(),headerMap,new Gson().toJson(event), HttpMethod.POST,String.class);
        log.info(responseEntity.getBody());
    }

    @Override
    public void endRegistration() {
        collectorExecuter.shutdown();
    }
}
