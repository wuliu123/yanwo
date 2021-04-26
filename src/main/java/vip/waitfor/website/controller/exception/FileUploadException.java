package vip.waitfor.website.controller.exception;

/**
 * 文件上传异常
 */
public class FileUploadException extends RequestException {
    private static final long serialVersionUID = 6671833869680850496L;

    public FileUploadException() {
    }

    public FileUploadException(String message) {
        super(message);
    }

    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadException(Throwable cause) {
        super(cause);
    }

    public FileUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
