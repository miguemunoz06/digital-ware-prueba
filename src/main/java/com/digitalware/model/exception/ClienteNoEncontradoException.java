
package com.digitalware.model.exception;

public class ClienteNoEncontradoException extends Exception {
    private static final long serialVersionUID = 1760660992183842633L;

    public ClienteNoEncontradoException() {
        super();
    }

    public ClienteNoEncontradoException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ClienteNoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClienteNoEncontradoException(String message) {
        super(message);
    }

    public ClienteNoEncontradoException(Throwable cause) {
        super(cause);
    }
}
