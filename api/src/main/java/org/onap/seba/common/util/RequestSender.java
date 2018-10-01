package org.onap.seba.common.util;

import org.onap.seba.common.exception.HTTPException;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

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

        URI uri = new URI(url);

        HttpEntity<String> entity = new HttpEntity<String>(body,httpHeaders);


       ResponseEntity<T> responseEntity = restTemplate.exchange(uri,httpMethod,entity, clazz);
       if(!responseEntity.getStatusCode().is2xxSuccessful())
           throw new HTTPException();

       return responseEntity;
    }
}
