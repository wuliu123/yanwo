package vip.waitfor.website.controller;



import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.waitfor.website.controller.exception.RequestException;
import vip.waitfor.website.service.exception.*;
import vip.waitfor.website.util.ResponseResult;

import javax.servlet.http.HttpSession;

/**
 * 当前项目中所有控制器的基类
 */
public abstract class BaseController {

    /*
    正确响应时的代号
     */
    public static final Integer SUCCESS = 200;

    @ExceptionHandler({ServiceException.class, RequestException.class})
    @ResponseBody
    public ResponseResult<Void> handleException(Exception e) {
        Integer state = null;
        if (e instanceof DuplicatekeyException) {
            state = 400;//400-违反了Unique约束的异常
        } else if (e instanceof UserNotFoundException) {
            state = 401;//401-用户数据不存在
        } else if (e instanceof PasswordNotMatchException) {
            state = 402;//402-用户密码不正确
        }  else if (e instanceof InsertException) {
            state = 500;//500-插入用户数据异常
        } else if (e instanceof UpdateException) {
            state = 501;//501-修改数据异常
        } else if (e instanceof DeleterException) {
            state = 502;//502-删除数据异常
        }
        return new ResponseResult<Void>(state, e);
    }

    /**
     * 从Session中获取uid
     *
     * @param session HttpSession对象
     * @return 当前登录的用户的id
     */
    protected Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 从Session中获取uid
     *
     * @param session httpSession对象
     * @return 当前登录的用户的用户名
     */
    protected String getUsernameFromSession(HttpSession session) {
        return String.valueOf(session.getAttribute("username").toString());
    }
}
