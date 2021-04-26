package vip.waitfor.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vip.waitfor.website.controller.exception.FileEmptyException;
import vip.waitfor.website.controller.exception.FileSizeOutOfLimitException;
import vip.waitfor.website.controller.exception.FileTypeNotSupportException;
import vip.waitfor.website.entity.Article;
import vip.waitfor.website.entity.CarouselMap;
import vip.waitfor.website.service.IArticleService;
import vip.waitfor.website.service.ICarouselMapService;
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
@RequestMapping("CarouserlMap")
public class CarouserlMapController extends BaseController {
    /**
     * 上传文件夹的名称
     */
    @Value("${out.resource.path}")
    private String path;
    private static final String UPLOAD_DIR_NAME = "Carouselmap";
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
    private ICarouselMapService iCarouselMapService;


    @GetMapping("/findAll")
    public ResponseResult<List<CarouselMap>> findCarouselMap() {
        List<CarouselMap> data = iCarouselMapService.findAllCaroueslMap();
        return new ResponseResult(SUCCESS, data);
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
        String parentPath = path + "/Carouselmap";
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
        iCarouselMapService.addcarouselMap(avatar,(String)session.getAttribute("username"));
        // 返回头像
        ResponseResult<String> rr = new ResponseResult<>();
        rr.setState(SUCCESS);
        rr.setData("/" + UPLOAD_DIR_NAME + "/" + fileName);
        return rr;

    }


    @GetMapping("/Delete/{id}")
    public ResponseResult<Void> deleteCarouselMap(@PathVariable("id") Integer id) {
        iCarouselMapService.delete(id);
        return new ResponseResult<Void>(SUCCESS);
    }


}
