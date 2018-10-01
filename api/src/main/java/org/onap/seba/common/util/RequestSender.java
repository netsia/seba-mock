package org.onap.seba.common.util;

import lombok.extern.slf4j.Slf4j;
import org.onap.seba.common.exception.HTTPException;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@Slf4j
public class RequestSender {
    private RequestSender(){}

    /**
     *
     * @param url which will send for
     * @param header which is header for call
     * @param body of the message
     * @param httpMethod GET,SET,PUT
     * @param clazz containing class
     * @param <T> return object type
     * @return
     * @throws URISyntaxException
     * @throws HTTPException
     */
    public static <T> ResponseEntity<T> sendRequest(String url,Map<String,String> header, String body,
                                                    HttpMethod httpMethod,Class clazz)
            throws URISyntaxException, HTTPException {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(header);
        log.info("sending rest req to uri {}", url);
        log.info("body is {}", body);
        URI uri = new URI(url);
        log.info("body is ok");

        HttpEntity<String> entity = new HttpEntity(body,httpHeaders);
        log.info("body is ok1");

        ResponseEntity<T> responseEntity = restTemplate.exchange(uri,httpMethod,entity, clazz);
        log.info("body is ok6");

        if(!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new HTTPException();
        }
        log.info("body is ok7");

       return responseEntity;
    }
}
