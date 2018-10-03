package org.onap.seba.core;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.onap.seba.common.util.CommonUtils;
import org.onap.seba.common.util.KafkaUtils;
import org.onap.seba.common.util.RequestSender;
import org.onap.seba.core.config.DmaapConfig;

import org.onap.seba.core.config.OsamConfig;
import org.onap.seba.core.config.PNFConfig;
import org.onap.seba.model.AccessPod;
import org.onap.seba.model.PNF;
import org.onap.seba.service.OsamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class KafkaConsumer {
    public static final String SLASH="/";

    private ScheduledExecutorService collectorExecuter = Executors.newScheduledThreadPool(1);
    private DmaapConfig dmpConfig;
    private OsamService osamService;
    private PNFConfig pnfConfig;
    private OsamConfig osamConfig;


    @Autowired
    public KafkaConsumer(DmaapConfig dmpConfig, PNFConfig pnfConfig, OsamConfig osamConfig,OsamService osamService) {
        this.dmpConfig = dmpConfig;
        this.osamService = osamService;
        this.pnfConfig = pnfConfig;
        this.osamConfig = osamConfig;
    }

    @PostConstruct
    public void before()
    {
        startConsuming();
    }

    public void startConsuming() {
        Runnable collectTask = new Runnable() {
            @Override
            public void run() {
                try {
                    consume();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Integer interval = Integer.parseInt(dmpConfig.getTimeInterval());
        collectorExecuter.scheduleWithFixedDelay(collectTask, interval, interval, TimeUnit.SECONDS);
    }

    private void consume() throws URISyntaxException {
        log.info("CONSUMING");
        String uri = CommonUtils.mergeFormat(dmpConfig.getDmaapUri(),SLASH,dmpConfig.getConsumerGroup()
                ,SLASH,dmpConfig.getConsumerId());

        ResponseEntity<List<String>> responseEntity =  RequestSender.sendRequest(uri,null,null, HttpMethod.GET, ArrayList.class);

        List<PNF> pnfList = responseEntity.getBody().stream().map(s -> new Gson().fromJson(s,PNF.class)).collect(Collectors.toList());
        log.info("CONSUMED {}",pnfList);
        if(pnfList != null)
        {
            PNF pnf = KafkaUtils.validatePNF(pnfList,pnfConfig.getCorrelationId());
            if(pnf != null)
            {

                AccessPod accessPod = KafkaUtils.convertPNFToAccessPod(pnf,pnfConfig.getNbPort(),pnfConfig.getXosIp()
                        ,pnfConfig.getPassword(), pnfConfig.getXosPort(),pnfConfig.getUsername());

                if(accessPod == null)
                    return;
                try {
                    log.info("PNF READY EVENT is received for {}. Osam core registration will begin",accessPod.getPnfId());
                    osamService.register(accessPod);
                    collectorExecuter.shutdown();
                }
                catch (Exception e)
                {
                    log.error("Connection is not available {}", osamConfig);
                }
            }
        }
    }

}
