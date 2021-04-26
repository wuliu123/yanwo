package vip.waitfor.website.service.exception;

/**
 * 插入用户数据异常
 * @author 刘先生
 * @date 2021/4/7
 */
public class InsertException extends ServiceException{
    private static final long serialVersionUID = 1282257556018383066L;

    public InsertException() {
    }

    public InsertException(String message) {
        super(message);
    }

    public InsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertException(Throwable cause) {
        super(cause);
    }

    public InsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
