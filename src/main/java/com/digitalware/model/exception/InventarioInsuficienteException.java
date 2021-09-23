
package com.digitalware.model.exception;

public class InventarioInsuficienteException extends Exception {
    private static final long serialVersionUID = -8001165507755235014L;

    public InventarioInsuficienteException() {
        super();
    }

    public InventarioInsuficienteException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InventarioInsuficienteException(String message, Throwable cause) {
        super(message, cause);
    }

    public InventarioInsuficienteException(String message) {
        super(message);
    }

    public InventarioInsuficienteException(Throwable cause) {
        super(cause);
    }
}
