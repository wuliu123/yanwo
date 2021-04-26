package vip.waitfor.website.controller.exception;

/**
 * 上传的文件类型不支持的异常
 */
public class FileTypeNotSupportException extends FileUploadException {
    private static final long serialVersionUID = 3979658906086053836L;

    public FileTypeNotSupportException() {
    }

    public FileTypeNotSupportException(String message) {
        super(message);
    }

    public FileTypeNotSupportException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileTypeNotSupportException(Throwable cause) {
        super(cause);
    }

    public FileTypeNotSupportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
