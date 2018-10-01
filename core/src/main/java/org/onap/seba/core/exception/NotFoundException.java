package org.onap.seba.core.exception;


/**
 * Created by cemturker on 18.09.2018.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
