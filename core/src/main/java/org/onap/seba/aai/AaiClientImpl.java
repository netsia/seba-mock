package org.onap.seba.aai;

import lombok.extern.slf4j.Slf4j;
import org.onap.seba.common.exception.BadFormatException;
import org.onap.seba.common.exception.InvalidOperationException;
import org.onap.seba.common.exception.NotFoundException;
import org.onap.seba.aai.config.AaiConfig;
import org.onap.seba.common.exception.ExternalSystemException;
import org.onap.seba.model.PNF;
import org.onap.seba.common.util.AaiHeaderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;

import javax.net.ssl.SSLException;


/**
 * Created by cemturker on 12.04.2018.
 */

@Service
@Slf4j
public class AaiClientImpl implements AaiClient {

    private AaiConfig aaiConfig;

    @Autowired
    public AaiClientImpl(AaiConfig aaiConfig) {
        this.aaiConfig = aaiConfig;
    }

    @Override
    public PNF queryPnf(String name) {
        try {
            ClientResponse response = AaiWebClient.webClient(aaiConfig).build().queryPNF(name);
            commonErrorCheck(name,response);
            log.info("Pnf query response code {} for {} id",response.statusCode(),name);
            if (response.statusCode().is2xxSuccessful()) {
                return AaiHeaderUtils.convertToPnf(response.bodyToMono(String.class).block());
            }
        } catch (SSLException e) {
            log.error("",e);
        }
        throw new InvalidOperationException("");
    }

    @Override
    public void putPnf(PNF pnf) {
        try {
            ClientResponse response = AaiWebClient.webClient(aaiConfig).build().putPNF(pnf);
            commonErrorCheck(pnf,response);
            log.info("Pnf query response code {} for {} ",response.statusCode(),pnf);
            if (response.statusCode().is2xxSuccessful()) {
                return;
            }
        } catch (SSLException e) {
            log.error("",e);
        }
        throw new InvalidOperationException("");
    }

    private <T> void commonErrorCheck(T t, ClientResponse response){
        if (response.statusCode().is5xxServerError()) {
            throw new ExternalSystemException("Aai error code:"+response.statusCode().value());
        }
        if (HttpStatus.BAD_REQUEST.equals(response.statusCode())) {
            throw new BadFormatException("Bad format exception is received from AAI");
        }
        if (HttpStatus.NOT_FOUND.equals(response.statusCode())) {
            throw new NotFoundException(t+" is not found in AAI");
        }
    }
}
