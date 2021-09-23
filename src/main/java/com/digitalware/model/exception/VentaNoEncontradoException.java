
package com.digitalware.model.exception;

public class VentaNoEncontradoException extends Exception {
    private static final long serialVersionUID = -8384191082770311297L;

    public VentaNoEncontradoException() {
        super();
    }

    public VentaNoEncontradoException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public VentaNoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }

    public VentaNoEncontradoException(String message) {
        super(message);
    }

    public VentaNoEncontradoException(Throwable cause) {
        super(cause);
    }
}
