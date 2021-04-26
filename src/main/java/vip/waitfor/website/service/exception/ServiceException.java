package vip.waitfor.website.service.exception;

/**
 * 业务异常，是当前项目中所有业务的基类
 * @author 刘先生
 * @date 2021/4/7
 */
public class ServiceException extends  RuntimeException {
    private static final long serialVersionUID = 8664798299909128344L;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
