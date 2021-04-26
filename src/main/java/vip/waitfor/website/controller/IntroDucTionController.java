package vip.waitfor.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vip.waitfor.website.entity.Article;
import vip.waitfor.website.entity.IntroDucTion;
import vip.waitfor.website.service.IArticleService;
import vip.waitfor.website.service.IIntroDucTionService;
import vip.waitfor.website.util.ResponseResult;

import javax.servlet.http.HttpSession;

/**
 * 简介数据-控制器层
 * @author 刘先生
 * @date 2021/4/9
 */
@RestController
@RequestMapping("IntroDucTion")
public class IntroDucTionController extends BaseController  {

    @Autowired
    private IIntroDucTionService iIntroDucTionService;

    @PostMapping("/insert")
    public ResponseResult<Void> registration(IntroDucTion introDucTion, HttpSession session) {
        //获取管理员用户名
        String username = getUsernameFromSession(session);
        //执行新增
       iIntroDucTionService.addNewArticle(introDucTion,username);
        //返回注册成功的数据
        return new ResponseResult<Void>(SUCCESS);
    }


    @GetMapping("/find")
    public ResponseResult<IntroDucTion> find(Integer id) {
        IntroDucTion data = iIntroDucTionService.findintroDucTion(id);
        System.err.println(data);
        return new ResponseResult<IntroDucTion>(SUCCESS, data);
    }



    @PostMapping("/change_info")
    public ResponseResult<Void> changeInfo(IntroDucTion introDucTion) {
        //执行修改
       iIntroDucTionService.update(introDucTion);
        //返回
        return new ResponseResult<>(SUCCESS);
    }



}
