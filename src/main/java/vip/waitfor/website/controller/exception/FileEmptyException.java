package vip.waitfor.website.controller.exception;

/**
 * 上传的文件为空的异常
 */
public class FileEmptyException extends FileUploadException {
    private static final long serialVersionUID = 6967961660710688875L;

    public FileEmptyException() {
    }

    public FileEmptyException(String message) {
        super(message);
    }

    public FileEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileEmptyException(Throwable cause) {
        super(cause);
    }

    public FileEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
