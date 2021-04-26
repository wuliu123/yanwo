package vip.waitfor.website.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vip.waitfor.website.entity.User;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestCase {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Test
    public void  inster(){
        User user = new User();
        user.setUid(1);
        user.setUsername("刘先生");
        user.setEmail("371598263@qq.com");
        user.setGender(0);
        user.setIsDelete(0);
        user.setPassword("123456");
        user.setSalt("d");
        user.setCreatedTime(new Date());
        user.setCreatedUser("刘先生");
        Integer rows = userMapper.addnew(user);
        System.err.println("rows=" + rows);
    }

    /**
     * 查询用户数据
     */
    @Test
    public void findByUsername() {
        String username = "刘先生";
        User user = userMapper.findByUsername(username);
        System.err.println(user);
    }
}
