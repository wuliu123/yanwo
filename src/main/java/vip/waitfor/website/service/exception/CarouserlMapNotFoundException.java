package vip.waitfor.website.service.exception;

/**
 * 轮播图不存在
 * @author Administrator
 * @date 2021/4/7
 */
public class CarouserlMapNotFoundException extends ServiceException {
    private static final long serialVersionUID = 8326424354769626739L;

    public CarouserlMapNotFoundException() {
    }

    public CarouserlMapNotFoundException(String message) {
        super(message);
    }

    public CarouserlMapNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CarouserlMapNotFoundException(Throwable cause) {
        super(cause);
    }

    public CarouserlMapNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
