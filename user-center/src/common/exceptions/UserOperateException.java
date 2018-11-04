package exceptions;

public class UserOperateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserOperateException() {
    }

    public UserOperateException(String message) {
        super(message);
    }

    public UserOperateException(Throwable cause) {
        super(cause);
    }

    public UserOperateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserOperateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
