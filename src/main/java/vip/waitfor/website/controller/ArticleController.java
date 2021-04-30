package vip.waitfor.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vip.waitfor.website.controller.exception.FileEmptyException;
import vip.waitfor.website.controller.exception.FileSizeOutOfLimitException;
import vip.waitfor.website.controller.exception.FileTypeNotSupportException;
import vip.waitfor.website.entity.Article;
import vip.waitfor.website.entity.User;
import vip.waitfor.website.service.IArticleService;
import vip.waitfor.website.util.ResponseResult;


import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 文章数据-控制器层
 * @author 刘先生
 * @date 2021/4/9
 */
@RestController
@RequestMapping("article")
public class ArticleController extends BaseController{

    /**
     * 上传文件夹的名称
     */
    @Value("${out.resource.path}")
    private String path;
    private static final String UPLOAD_DIR_NAME = "imgUrl";
    /**
     * 上传文件的最大大小
     */
    private static final long FILE_MAX_SIZE =  1 * 1024 * 1024;

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
    private IArticleService iArticleService;


    @RequestMapping("/find")
    public ResponseResult<Object> findAllGoods(@RequestParam(value = "page",defaultValue = "1") int page, @RequestParam(value = "size",defaultValue = "10") int size) {
        Object Article = this.iArticleService.findArticle(page, size);
        return new ResponseResult<Object>(SUCCESS, Article);
    }

    @RequestMapping("/findpractice")
    public ResponseResult<Object> findPractice(@RequestParam(value = "page",defaultValue = "1") int page, @RequestParam(value = "size",defaultValue = "10") int size,String tag) {
        Object Article = this.iArticleService.findByAll(page, size, tag);
        return new ResponseResult<Object>(SUCCESS, Article);
    }


    @GetMapping("/findHotlist")
    public ResponseResult<List<Article>> findHotlist() {
        List<Article> data = iArticleService.findAllHotlist();
        return new ResponseResult<List<Article>>(SUCCESS, data);
    }

    @PostMapping("/findOne")
    public ResponseResult<Article> findAricle(Integer wid) {
        Article data = iArticleService.find(wid);
        return new ResponseResult<Article>(SUCCESS, data);
    }



    @PostMapping("/insert")
    public ResponseResult<Void> registration(Article article, HttpSession session) {
        //获取管理员用户名
        String username = getUsernameFromSession(session);
        Integer uid = getUidFromSession(session);
        //执行新增
        iArticleService.addNewArticle(article,username,uid);
        //返回注册成功的数据
        return new ResponseResult<Void>(SUCCESS);
    }




    @PostMapping("/insertImg")
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
        //String parentPath = session.getServletContext().getRealPath(UPLOAD_DIR_NAME);
        String parentPath = path ;
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
//        Integer uid = getUidFromSession(session);
        // 更新头像数据
        String avatar = "/" + UPLOAD_DIR_NAME + "/" + fileName;
        // 返回头像
        ResponseResult<String> rr = new ResponseResult<>();
        rr.setState(SUCCESS);
        rr.setData("/" + UPLOAD_DIR_NAME + "/" + fileName);
        return rr;

    }


    @PostMapping("/change_info")
    public ResponseResult<Void> changeInfo(Article article) {
        //执行修改
        iArticleService.update(article);
        //返回
        return new ResponseResult<>(SUCCESS);
    }

    @PostMapping("/changeHotlist/{wid}")
    public ResponseResult<Void> changeHotlist(@PathVariable("wid") Integer wid) {
        System.err.println("wid:" + wid);
        System.err.println("修改热榜运行了");
        //执行修改
        iArticleService.updateHot(wid);

        //返回
        return new ResponseResult<>(SUCCESS);
    }




    @GetMapping("/Delete/{id}")
    public ResponseResult<Void> deleteCarouselMap(@PathVariable("id") Integer id) {
        iArticleService.delete(id);
        return new ResponseResult<Void>(SUCCESS);
    }

}
