package com.qulix.serovdo.core.exception;

public class EntityExtractionFailedException extends Exception{
    public EntityExtractionFailedException() {
    }

    public EntityExtractionFailedException(String message) {
        super(message);
    }

    public EntityExtractionFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityExtractionFailedException(Throwable cause) {
        super(cause);
    }
}
