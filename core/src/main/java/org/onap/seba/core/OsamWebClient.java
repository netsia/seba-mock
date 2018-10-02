package org.onap.seba.core;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.onap.seba.common.exception.BadFormatException;
import org.onap.seba.common.exception.ExternalSystemException;
import org.onap.seba.common.exception.InvalidOperationException;
import org.onap.seba.common.exception.NotFoundException;
import org.onap.seba.common.util.OsamUtils;
import org.onap.seba.common.util.RequestSender;
import org.onap.seba.core.config.OsamConfig;
import org.onap.seba.model.AccessPod;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotNull;
import java.net.URISyntaxException;

/**
 * Created by cemturker on 02.10.2018.
 */
@Slf4j
public class OsamWebClient {
    private OsamConfig config;
    private String body;
    private String pnfId;
    private OsamWebClient(OsamConfig config){
        this.config = config;
    }

    public static OsamWebClient client(@NotNull OsamConfig config) {
        return new OsamWebClient(config);
    }

    public void withRegister(AccessPod accessPod) {
        this.body = new Gson().toJson(accessPod,AccessPod.class);
        String url = OsamUtils.postAccessPodUrl(config.getIp(),config.getPort());
        sendRequest(url,HttpMethod.POST);
    }

    private void sendRequest(String url, HttpMethod method) {
        try {
            log.info("Request url is {}",url);
            log.info("Request body is {}",body);
            ResponseEntity<String> responseEntity = RequestSender.sendRequest(url, OsamUtils.headers(),body, method,String.class);
            handleResponse(responseEntity);
        } catch (URISyntaxException e) {
            log.error("",e);
            throw new InvalidOperationException(e.getMessage());
        }
    }

    public void withRemove(String pnfId) {
        this.pnfId = pnfId;
        String url = OsamUtils.deleteAccessPod(config.getIp(),config.getPort(),pnfId);
        sendRequest(url,HttpMethod.DELETE);
    }

    private <T> void handleResponse(ResponseEntity<T> responseEntity){
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return;
        }
        if (HttpStatus.BAD_REQUEST.equals(responseEntity.getStatusCode())) {
            throw new BadFormatException("Body" + body + " is not proper");
        }
        if (HttpStatus.NOT_FOUND.equals(responseEntity.getStatusCode())) {
            throw new NotFoundException("Pnf Id: "+pnfId);
        }
        if (responseEntity.getStatusCode().is5xxServerError()) {
            throw new ExternalSystemException("Osam is not reachable");
        }
    }
}
