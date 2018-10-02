package org.onap.seba.common.exception;


/**
 * Created by cemturker on 18.09.2018.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
