package vip.waitfor.website.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vip.waitfor.website.controller.exception.FileEmptyException;
import vip.waitfor.website.controller.exception.FileSizeOutOfLimitException;
import vip.waitfor.website.controller.exception.FileTypeNotSupportException;
import vip.waitfor.website.entity.User;
import vip.waitfor.website.service.IUserService;
import vip.waitfor.website.util.ResponseResult;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 用户数据-控制器层
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    /**
     * 上传文件夹的名称
     */
    private static final String UPLOAD_DIR_NAME = "upload";
    /**
     * 上传文件的最大大小
     */
    private static final long FILE_MAX_SIZE = 5 * 1024 * 1024;

    /**
     * 允许上传的文件类型
     */
    private static final List<String> FILE_CONTENT_TYPES = new ArrayList<>();

    /**
     * 初始化允许上传的文件类型的集合
     */
    static {
        FILE_CONTENT_TYPES.add("image/jpeg");
        FILE_CONTENT_TYPES.add("image/png");

    }

    @Autowired
    private IUserService iUserService;


    @CrossOrigin
    @RequestMapping("/reg")
    public ResponseResult<Void> registration(User user) {
        //执行注册
        iUserService.reg(user);
        //返回注册成功的数据
        return new ResponseResult<Void>(SUCCESS);
    }

    // 退出登录
    @RequestMapping("/outLogin")
    public ResponseResult<Void> outLogin(HttpSession session) {
        // 通过session.invalidata()方法来注销当前的session
        session.invalidate();
        return new ResponseResult<Void>(SUCCESS);
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseResult<User> handleLogin(@RequestParam("username") String username,
                                            @RequestParam("password") String password, HttpSession session) {
        //执行登录
        User user = iUserService.login(username, password);
        //将相关信息存入Session中
        session.setAttribute("uid", user.getUid());
        session.setAttribute("username", user.getUsername());
        //返回登录成功的数据
        return new ResponseResult<>(SUCCESS, user);
    }


    @PostMapping("/password")
    public ResponseResult<Void> changePassword(@RequestParam("old_password") String oldPassword,
                                               @RequestParam("new_password") String newPassword, HttpSession session) {
        // 获取当前登录的用户的id
        Integer uid = getUidFromSession(session);
        // 执行修改密码
        iUserService.changePassword(uid, oldPassword, newPassword);
        return new ResponseResult<>(SUCCESS);

    }


    @RequestMapping("/info")
    public ResponseResult<User> getInfo(HttpSession session) {
        //获取当前登录用户uid
        Integer uid = getUidFromSession(session);
        //根据uid查询用户数据
        User user = iUserService.getById(uid);
        //返回
        return new ResponseResult<>(SUCCESS, user);
    }



    @PostMapping("/change_info")
    public ResponseResult<Void> changeInfo(User user, HttpSession session) {
        //获取当前登录用的uid
        Integer uid = getUidFromSession(session);
        //将uid封装带参数user中，因为user是用户提交的数据，不包含uid
        user.setUid(uid);
        //执行修改
        iUserService.changeInfo(user);
        //返回
        return new ResponseResult<>(SUCCESS);
    }



    @PostMapping("/change_avatar")
    public ResponseResult<String> handleUpload(HttpSession session, @RequestParam("file") MultipartFile file) {
        // 检查是否存在上传文件 > file.isEmpty()
        if (file.isEmpty()) {
            // 抛出异常：文件不允许为空
            throw new FileEmptyException("上传失败！没有选择上传的文件，或选中的文件为空");
        }
        // 检查文件大小 >file.getSize()
        if (file.getSize() > FILE_MAX_SIZE) {
            // 抛出异常：文件大小超出限制
            throw new FileSizeOutOfLimitException("您上传文件的大小超过了："+FILE_MAX_SIZE);
        }
        // 检查文件类型 > file.getContentType()
        if (!FILE_CONTENT_TYPES.contains(file.getContentType())) {
            // 抛出异常：文件类型限制
            throw new FileTypeNotSupportException("您上传的图片格式不对，只能上传以下格式："+FILE_CONTENT_TYPES);
        }
        // 确定上传文件夹的路径
        // session.getServletContext.getRealPath (UPLOAD_DIR_NAME)
        // > exists() > mkdirs()
        String parentPath = session.getServletContext().getRealPath(UPLOAD_DIR_NAME);
        File parent = new File(parentPath);
        if (!parent.exists()) {
            parent.mkdirs();
        }
        // 确定文件名 > getOriginalFileName()
        String originalFileName = file.getOriginalFilename();
        int beginIndex = originalFileName.lastIndexOf(".");
        String suffox = originalFileName.substring(beginIndex);
        String fileName = System.currentTimeMillis() + "" + (new Random().nextInt(900000) + 100000) + suffox;
        // 确定文件
        File dest = new File(parent, fileName);
        // 执行保存文件
        try {
            file.transferTo(dest);
            System.err.println("上传成功");
        } catch (IllegalStateException e) {
            // 抛出异常：上传失败！
        } catch (IOException e) {
            // 抛出异常：上传失败！
        }
        // 获取当前用户的id
        Integer uid = getUidFromSession(session);
        // 更新头像数据
        String avatar = "/" + UPLOAD_DIR_NAME + "/" + fileName;
        iUserService.changeAvatar(uid, avatar);
        // 返回头像
        ResponseResult<String> rr = new ResponseResult<>();
        rr.setState(SUCCESS);
        rr.setData("/" + UPLOAD_DIR_NAME + "/" + fileName);
        return rr;
    }


}
