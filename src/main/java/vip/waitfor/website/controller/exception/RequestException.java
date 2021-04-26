package vip.waitfor.website.controller.exception;

/**
 * 请求异常，是当前项目中控制器类抛出异常的基类
 */
public class RequestException extends RuntimeException {
    private static final long serialVersionUID = 8083372356700112577L;

    public RequestException() {
    }

    public RequestException(String message) {
        super(message);
    }

    public RequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestException(Throwable cause) {
        super(cause);
    }

    public RequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
