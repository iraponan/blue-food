package br.eti.inovareti.bluefood.application.service;

public class ApplicationServiceException extends RuntimeException {

    public ApplicationServiceException(String message) {
        super(message);
    }

    public ApplicationServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationServiceException(Throwable cause) {
        super(cause);
    }
}
