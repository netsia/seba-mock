package org.onap.seba.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cemturker on 02.10.2018.
 */
@RestController
@Slf4j
@RequestMapping("/run")
public class XosMockController {

    @PostMapping
    public ResponseEntity<String> postRequest(@RequestBody String body){
        log.info("Received body {}",body);
        return ResponseEntity.ok().build();
    }
}
