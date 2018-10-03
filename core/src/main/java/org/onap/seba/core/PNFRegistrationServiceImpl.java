package org.onap.seba.core;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.onap.seba.common.exception.HTTPException;
import org.onap.seba.common.exception.NullParameterException;
import org.onap.seba.common.util.ModelUtils;
import org.onap.seba.common.util.RequestSender;
import org.onap.seba.core.config.PNFConfig;
import org.onap.seba.model.CommonEventHeader;
import org.onap.seba.model.Event;
import org.onap.seba.model.PnfRegistrationFields;
import org.onap.seba.model.VesEvent;
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


        collectorExecuter.scheduleWithFixedDelay(collectTask,0,Integer.parseInt(pnfConfig.getPnfRegisterTimeInterval()), TimeUnit.SECONDS);

    }

    private void registerPNF() throws NullParameterException, HTTPException, URISyntaxException {
        CommonEventHeader commonEventHeader = ModelUtils.getDefaultPnfCommontEventHeader(pnfConfig.getCorrelationId());
        PnfRegistrationFields pnfRegistrationFields = ModelUtils.createPnfRegistration(
                pnfConfig.getPnfMacAddress(), pnfConfig.getPnfIpv4Address(), pnfConfig.getPnfIpv6Address());
        Event event = new Event(commonEventHeader,pnfRegistrationFields);
        VesEvent vesEvent = new VesEvent("v7",event);
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type","application/json");
        log.info("PNF Registration Event is going to be sent");
        ResponseEntity<String> responseEntity = RequestSender.sendRequest(pnfConfig.getVesUrl(),headerMap,new Gson().toJson(vesEvent), HttpMethod.POST,String.class);
        log.info(responseEntity.getBody());
        if(!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new HTTPException("http failed code:"+responseEntity.getStatusCode().value());
        }
    }

    @Override
    public void endRegistration() {
        collectorExecuter.shutdown();
    }
}
