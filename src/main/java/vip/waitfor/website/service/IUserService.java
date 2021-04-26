package vip.waitfor.website.service;


import vip.waitfor.website.entity.User;
import vip.waitfor.website.service.exception.*;

/**
 * 处理用户数据的业务层接口
 * @author 刘先生
 * @date 2021/4/7
 */
public interface IUserService {
    /**
     * 用户注册方法
     *
     * @param user 用户注册信息
     * @return
     * @throws DuplicatekeyException 向数据库插入数据异常
     * @throws InsertException       插入用户数据异常
     */
    User reg(User user) throws DuplicatekeyException, InsertException;

    /**
     * 用户登录方法
     *
     * @param username 用户名
     * @param password 用户密码
     * @return 返回成功登录用户的数据
     * @throws UserNotFoundException     用户名不存在
     * @throws PasswordNotMatchException 密码错误
     */
    User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException;


    /**
     * 修改密码
     *
     * @param uid         用户uid
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @throws UserNotFoundException
     * @throws PasswordNotMatchException
     * @throws UpdateException
     */
    void changePassword(Integer uid, String oldPassword, String newPassword) throws UserNotFoundException, PasswordNotMatchException, UpdateException;


    /**
     * 修改用户资料
     *
     * @param user 用户数据
     * @throws UserNotFoundException
     * @throws UpdateException
     */
    void changeInfo(User user) throws UserNotFoundException, UpdateException;


    /**
     * 修改用户头像
     *
     * @param uid
     * @param avatar
     * @throws UserNotFoundException
     * @throws UpdateException
     */
    void changeAvatar(Integer uid, String avatar) throws UserNotFoundException, UpdateException;


    /**
     * 根据用户uid 查询用户数据
     *
     * @param uid
     * @return
     */
    User getById(Integer uid);
}
