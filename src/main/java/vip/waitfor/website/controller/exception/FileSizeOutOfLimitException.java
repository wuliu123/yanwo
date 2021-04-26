package vip.waitfor.website.controller.exception;

/**
 * 上传的文件超过了限制的异常
 */
public class FileSizeOutOfLimitException extends FileUploadException {
    private static final long serialVersionUID = 781135109968797509L;

    public FileSizeOutOfLimitException() {
    }

    public FileSizeOutOfLimitException(String message) {
        super(message);
    }

    public FileSizeOutOfLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSizeOutOfLimitException(Throwable cause) {
        super(cause);
    }

    public FileSizeOutOfLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
