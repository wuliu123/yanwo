package vip.waitfor.website.mapper;



import org.apache.ibatis.annotations.Param;
import vip.waitfor.website.entity.User;

import java.util.Date;

/**
 * 处理用户数据的持久层
 * @author 刘先生
 * @date 2021/4/7
 */
public interface UserMapper {

    /**
     * 插入用户数据
     *
     * @param user 用户数据
     * @return 受影响的行数
     */
    Integer addnew(User user);

    /**
     * 根据用户查询用户数据
     *
     * @param username 用户名
     * @return 匹配的用户数据，如果没有匹配的数据，则返回null
     */
    User findByUsername(String username);

    /**
     * 更新用户密码
     *
     * @param uid          用户的UID
     * @param password     新密码
     * @param modifiedUser 最后修改的人
     * @param modifiedTime 最后修改的时间
     * @return 返回受影响的行数
     */
    Integer updatePassword(
            @Param("uid") Integer uid,
            @Param("password") String password,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime
    );


    /**
     * 更新用户资料
     *
     * @param user 用户数据
     * @return 返回受影响的行数
     */
    Integer updateInfo(User user);


    /**
     * 更新用户头像
     *
     * @param uid          用户uid
     * @param avatar       头像路径
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateAvatar(
            @Param("uid") Integer uid,
            @Param("avatar") String avatar,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime
    );

    /**
     * 根据用户uid查询用户数据
     *
     * @param uid
     * @return
     */
    User findById(Integer uid);

}
