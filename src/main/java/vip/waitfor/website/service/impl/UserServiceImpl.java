package vip.waitfor.website.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import vip.waitfor.website.entity.User;
import vip.waitfor.website.mapper.UserMapper;
import vip.waitfor.website.service.IUserService;
import vip.waitfor.website.service.exception.*;

import java.util.Date;
import java.util.UUID;

/**
 * 处理用户数据的业务层实现类
 * @author 刘先生
 * @date 2021/4/7
 */
@Service
public class UserServiceImpl implements IUserService {


    @Autowired(required = false)
    private UserMapper userMapper;

    /**
     * 注册业务
     *
     * @param user 用户注册信息
     * @return
     * @throws DuplicatekeyException
     * @throws InsertException
     */
    @Override
    public User reg(User user) throws DuplicatekeyException, InsertException {
        //根据尝试注册的用户名查询用户数据
        User data = findByUsername(user.getUsername());
        //判断查询到的数据是否为null
        if (data == null) {
            //是：用户名不存在，允许注册，则执行注册
            //补充非用户提交的数据
            user.setIsDelete(0);
            //四项日志
            Date now = new Date();
            user.setCreatedUser(user.getUsername());
            user.setCreatedTime(now);
            user.setModifiedUser(user.getUsername());
            user.setModifiedTime(now);

            //加密-1：获取随机的UUID为盐值
            String salt = UUID.randomUUID().toString();
            //加密-2：获取用户提交的原始密码
            String srcPassword = user.getPassword();
            //加密-3：基于原始密码和颜值执行加密，获取通过MD5加密
            String md5Password = getMd5Password(srcPassword, salt);
            //加密-4：将加密后的密码封装在user对象中
            user.setPassword(md5Password);
            //加密-5：将盐值存入user对象中
            user.setSalt(salt);
            //执行注册
            addnew(user);
        } else {
            //否：用户名已被占用，抛出DuplicatekeyException异常
            throw new DuplicatekeyException("注册失败！尝试注册的用户名（" + user.getUsername() + ")已经被占用！");
        }
        return data;
    }

    ;

    /**
     * 用户登录业务
     *
     * @param username 用户名
     * @param password 用户密码
     * @return
     * @throws UserNotFoundException
     * @throws PasswordNotMatchException
     */
    @Override
    public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
        // 根据参数username查询用户数据
        User data = findByUsername(username);
        // 判断用户数据是否为null
        if (data == null) {
            // 是：为null,用户名不存在，则抛出UserNotFoundException
            throw new UserNotFoundException("您尝试登陆的用户名不存在");
        }
        // 否：非null,用户名存在，根据用户名找到数据，取出盐值
        String salt = data.getSalt();
        // 对参数password执行加密
        String md5Password = getMd5Password(password, salt);
        // 判断密码是否正确
        if (data.getPassword().equals(md5Password)) {
            // 是：匹配，密码正确，判断是否被删除
            if (data.getIsDelete() == 1) {
                // 是：已被删除，则抛出UserNotFoundException
                throw new UserNotFoundException("登陆失败！您的账户已被注销！");
            }
            // 否：没有被删除，则登录成功，将第一步查询的用户数据中的盐值和密码设置为null，
            data.setPassword(null);
            data.setSalt(null);
            // 返回用户数据
            return data;
        } else {
            // 否：不匹配，密码错误，则抛出PassworNotMatchException
            throw new PasswordNotMatchException("登陆失败！密码错误");
        }

    }

    ;

    /**
     * 修改用户密码业务
     *
     * @param uid         用户uid
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @throws UserNotFoundException
     * @throws PasswordNotMatchException
     * @throws UpdateException
     */
    @Override
    public void changePassword(Integer uid, String oldPassword, String newPassword) throws UserNotFoundException, PasswordNotMatchException, UpdateException {
        //根据用户uid查询用户数据
        User data = findById(uid);
        //判断查询结果是否为null
        if (data == null) {
            //是：抛出异常：UserNotFoundException
            throw new UserNotFoundException("修改密码失败！您尝试访问的数据不存在！");
        }
        //判断查询结果中的isDelete是否为1
        if (data.getIsDelete() == 1) {
            //是：抛出异常：UserNotFoundException
            throw new UserNotFoundException("修改密码失败！您尝试访问的数据不存在！");
        }

        //取出查询结果中的盐值
        String salt = data.getSalt();
        //对参数oldPassword执行MD5加密
        String oldMd5Password = getMd5Password(oldPassword, salt);
        //将加密结果与查询结果中的password对比是否匹配
        if (data.getPassword().equals(oldMd5Password)) {
            //是：原密码正确，对参数newPassword执行MD5加密
            String newMd5Password = getMd5Password(newPassword, salt);
            //获取当前时间
            Date now = new Date();
            //更新密码
            updatePassword(uid, newMd5Password, data.getUsername(), now);
        } else {
            //否：原密码错误，抛出异常PasswordNotMatchException
            throw new PasswordNotMatchException("修改密码失败！！原密码错误！！");
        }
    }

    /**
     * 修改用户资料业务
     *
     * @param user 用户数据
     * @throws UserNotFoundException
     * @throws UpdateException
     */
    @Override
    public void changeInfo(User user) throws UserNotFoundException, UpdateException {
        //根据user.getId()查询用户数据
        User data = getById(user.getUid());
        //判断数据是否为null
        if (data == null) {
            //是：抛出：UserNotFoundException
            throw new UserNotFoundException("修改个人资料失败！您尝试访问的用户数据不存在");
        }

        //判断id_delete是否为1
        if (data.getIsDelete() == 1) {
            //是：抛出：UserNotFoundException
            throw new UserNotFoundException("修改个人资料失败！您尝试访问的用户数据不存在");
        }


        //向参数对象中封装：
        // - -modified_user > data.getUsername()
        // - -modified_time > new Date()
        user.setModifiedUser(data.getUsername());
        user.setModifiedTime(new Date());
        // 执行修改：
        updateInfo(user);
    }

    /**
     * 修改用户头像业务
     *
     * @param uid
     * @param avatar
     * @throws UserNotFoundException
     * @throws UpdateException
     */
    @Override
    public void changeAvatar(Integer uid, String avatar) throws UserNotFoundException, UpdateException {
        //根据参数uid查询用户数据
        User data = findById(uid);
        //判断是否为null
        if (data == null) {
            //是：UserNotFoundException
            throw new UserNotFoundException("修改头像失败！，您尝试访问的数据不存在");
        }
        //判断isDelete ==1
        if (data.getIsDelete() == 1) {
            //是：UserNotFoundException
            throw new UserNotFoundException("修改头像失败！，您尝试访问的数据不存在");
        }
        //执行更新头像
        String modifiedUser = data.getUsername();
        Date modifiedTime = new Date();
        updateAvatar(uid, avatar, modifiedUser, modifiedTime);
    }


    /**
     * 根据用户uid查询用户数据
     *
     * @param uid
     * @return
     */
    @Override
    public User getById(Integer uid) {
        // 根据uid查询用户数据
        User data = findById(uid);
        // 将不需要用的到数据设为null
        data.setPassword(null);
        data.setSalt(null);
        // 返回获取到的数据
        return data;
    }


    /**
     * 插入用户数据
     *
     * @param user
     */
    private void addnew(User user) {
        // 调用持久层方法
        Integer rows = userMapper.addnew(user);
        // 判断用户插入是否成功
        if (rows != 1) {
            throw new InsertException("新增用户数据时出现未知错误！");
        }
    };




    /**
     * 修改密码
     *
     * @param uid          用户uid
     * @param password     用户密码
     * @param modifiedUser
     * @param modifiedTime
     */
    private void updatePassword(Integer uid, String password, String modifiedUser, Date modifiedTime) {
        Integer rows = userMapper.updatePassword(uid, password, modifiedUser, modifiedTime);
        if (rows != 1) {
            throw new UpdateException("更新密码失败！更新密码时出现未知错误！");
        }

    }

    /**
     * 修改用户资料
     *
     * @param user 用户数据
     */
    private void updateInfo(User user) {
        //执行更新，获取返回值
        Integer rows = userMapper.updateInfo(user);
        //判断返回值，出错则抛出“更新时的未知错误异常”
        if (rows != 1) {
            throw new UpdateException("更新用户数据时出现未知错误！！！");
        }
    }


    /**
     * x修改用户头像
     *
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     */
    private void updateAvatar(Integer uid, String avatar, String modifiedUser, Date modifiedTime) {
        Integer rows = userMapper.updateAvatar(uid, avatar, modifiedUser, modifiedTime);
        if (rows != 1) {
            throw new UpdateException("更新头像时出现未知错误！");
        }
    }


    /**
     * 根据用户uid查询用户资料
     *
     * @param uid
     * @return
     */
    private User findById(Integer uid) {

        return userMapper.findById(uid);
    }

    /**
     * 根据用户名查询用户数据
     *
     * @param username 用户名
     * @return 匹配的用户数据，如果没有匹配的数据，则返回null
     */
    private User findByUsername(String username) {
        // 直接返回查到的数据
        return userMapper.findByUsername(username);
    }

    ;

    /**
     * 获取md5版的密码
     *
     * @param Password
     * @param salt
     * @return
     */
    private String getMd5Password(String Password, String salt) {
        // 盐值 拼接 原密码 拼接 盐值 （自由设计）
        String str = salt + Password + salt;
        // 循环执行10次摘要运算
        for (int i = 0; i < 10; i++) {
            str = DigestUtils.md5DigestAsHex(str.getBytes());
        }
        return str;
    }


}
