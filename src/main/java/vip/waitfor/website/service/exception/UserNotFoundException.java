package vip.waitfor.website.service.exception;

/**
 * 用户名不存在
 * @author Administrator
 * @date 2021/4/7
 */
public class UserNotFoundException extends ServiceException {
    private static final long serialVersionUID = 8326424354769626739L;

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
