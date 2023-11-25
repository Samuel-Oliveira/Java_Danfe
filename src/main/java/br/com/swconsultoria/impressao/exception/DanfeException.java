package br.com.swconsultoria.impressao.exception;

public class DanfeException extends RuntimeException {

    public DanfeException(String message) {
        super(message);
    }

    public DanfeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DanfeException(Throwable cause) {
        super(cause);
    }
}
