package br.com.likedit.core.exception;

import java.util.Objects;

public abstract class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 2726992561955759173L;

    protected final Codes code;

    public BusinessException(final Codes code) {
        super();
        Objects.requireNonNull(code);
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.code.getMessage();
    }

    public long getCode() {
        return this.code.getCode();
    }

    public Codes getCodeEnum() {
        return this.code;
    }

    public enum Codes {
        USER_NOT_FOUND(4004, "User not found");

        private final long code;

        private final String message;

        Codes(final long code, final String message) {
            Objects.requireNonNull(message);
            this.code = code;
            this.message = message;
        }

        public long getCode() {
            return this.code;
        }

        public String getMessage() {
            return this.message;
        }
    }
}
