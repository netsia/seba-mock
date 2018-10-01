package org.onap.seba.aai;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.onap.seba.aai.config.AaiConfig;
import org.onap.seba.aai.model.PNF;
import org.onap.seba.aai.util.AaiHeaderUtil;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

import javax.net.ssl.SSLException;
import java.net.URI;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

/**
 * Created by cemturker on 01.10.2018.
 */
@Slf4j
public class AaiWebClient {
    private AaiConfig config;
    private WebClient webClient;
    private AaiWebClient(AaiConfig config) {
        this.config = config;
    }

    public static AaiWebClient webClient(AaiConfig config) {
        return new AaiWebClient(config);
    }

    public AaiWebClient build() throws SSLException {
        SslContext sslContext;
        sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();
        log.debug("Setting ssl context");

        this.webClient =  WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(clientOptions -> {
                    clientOptions.sslContext(sslContext);
                    clientOptions.disablePool();
                }))
                .defaultHeaders(httpHeaders -> httpHeaders.setAll(AaiHeaderUtil.headers()))
                .filter(basicAuthentication(config.getUsername(), config.getPassword()))
                .build();
        return this;
    }

    public ClientResponse queryPNF(String pnfId) {
        return this.webClient.get().uri(getUri(pnfId)).exchange().block();
    }

    public ClientResponse putPNF(PNF pnf) {
        String aai = AaiHeaderUtil.convertPnfToString(pnf);
        System.out.println(aai);
        return this.webClient.put()
                .uri(getUri(pnf.getPnfName()))
                .body(Mono.just(aai),String.class)
                .exchange().block();
    }

    private URI getUri(String pnfName) {
        return new DefaultUriBuilderFactory().builder().scheme(config.getProtocol())
                .host(config.getHost()).port(config.getPort())
                .path(config.getBasePath() + config.getPnfPath() + "/" + pnfName).build();
    }


}
