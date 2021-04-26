package vip.waitfor.website.service.exception;

/**
 * 违反了Unique约束的异常,向数据库插入数据异常
 * @author 刘先生
 * @date 2021/4/7
 */
public class DuplicatekeyException extends ServiceException {
    private static final long serialVersionUID = 8904859705586471167L;

    public DuplicatekeyException() {
    }

    public DuplicatekeyException(String message) {
        super(message);
    }

    public DuplicatekeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatekeyException(Throwable cause) {
        super(cause);
    }

    public DuplicatekeyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
