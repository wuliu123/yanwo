package vip.waitfor.website.service.exception;

/**
 * 文章数据查询不到
 * @author 刘先生
 * @date 2021/4/13
 */
public class ArticleNotFoundExcption extends ServiceException {
    private static final long serialVersionUID = -9043243714599030394L;

    public ArticleNotFoundExcption() {
    }

    public ArticleNotFoundExcption(String message) {
        super(message);
    }

    public ArticleNotFoundExcption(String message, Throwable cause) {
        super(message, cause);
    }

    public ArticleNotFoundExcption(Throwable cause) {
        super(cause);
    }

    public ArticleNotFoundExcption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
