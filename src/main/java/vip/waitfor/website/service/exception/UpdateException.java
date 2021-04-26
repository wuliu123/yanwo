package vip.waitfor.website.service.exception;

/**
 * 更新数据异常
 */
public class UpdateException extends ServiceException {
    private static final long serialVersionUID = -3426647274907419117L;

    public UpdateException() {
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }

    public UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
