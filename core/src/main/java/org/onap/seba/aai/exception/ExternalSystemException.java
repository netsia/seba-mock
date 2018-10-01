package org.onap.seba.aai.exception;

/**
 * Created by cemturker on 26.09.2018.
 */
public class ExternalSystemException extends RuntimeException {
    public ExternalSystemException(String message) {
        super(message);
    }
}
