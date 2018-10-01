package org.onap.seba.common.exception;

public class HTTPException extends Exception{
        private static final String UNSUCCESSFUL_CALL = "REST CALL IS NULL";

        public HTTPException(){
            super(UNSUCCESSFUL_CALL);
        }

        public HTTPException(String message)
        {
            super(message);
        }
}

