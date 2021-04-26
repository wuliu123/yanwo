package vip.waitfor.website.service.exception;

/**
 * 删除数据异常
 * @author 刘先生
 * @date 2021/4/7
 */
public class DeleterException extends ServiceException{
    private static final long serialVersionUID = 1282257556018383066L;

    public DeleterException() {
    }

    public DeleterException(String message) {
        super(message);
    }

    public DeleterException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleterException(Throwable cause) {
        super(cause);
    }

    public DeleterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
