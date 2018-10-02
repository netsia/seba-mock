package org.onap.seba.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
     */
    public static <T> ResponseEntity<T> sendRequest(String url,Map<String,String> header, String body,
                                                    HttpMethod httpMethod,Class clazz)
            throws URISyntaxException {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(header);
        log.info("sending rest req to uri {}", url);
        log.info("body is {}", body);
        URI uri = new URI(url);

        HttpEntity<String> entity = new HttpEntity(body,httpHeaders);

        return restTemplate.exchange(uri,httpMethod,entity, clazz);
    }
}
