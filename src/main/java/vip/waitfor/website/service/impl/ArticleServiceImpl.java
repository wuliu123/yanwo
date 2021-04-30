package vip.waitfor.website.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.waitfor.website.entity.Article;
import vip.waitfor.website.mapper.ArticleMapper;
import vip.waitfor.website.service.IArticleService;
import vip.waitfor.website.service.exception.*;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired(required = false)
    private ArticleMapper articleMapper;

    @Override
    public Article addNewArticle(Article article,String userName,Integer uid) throws DuplicatekeyException, InsertException {
        //补充非用户提交的数据
        article.setIsDelete(0);
        article.setUid(uid);
        //四项日志
        Date now = new Date();
        article.setCreatedUser(userName);
        article.setCreatedTime(now);
        article.setModifiedUser(userName);
        article.setModifiedTime(now);
        //执行插入
        insert(article);
        return article;
    }

    @Override
    public Object findArticle(int page, int size) {
        PageHelper.startPage(page, size);
        List<Article> userList = findAllArticle();
        PageInfo<Article> pageInfo = new PageInfo(userList);
        return pageInfo;
    }

    @Override
    public Object findByAll(int page, int size,String tag) {
        PageHelper.startPage(page, size);
        List<Article> datalist = findAll(tag);
        PageInfo<Article> pageInfo = new PageInfo(datalist);
        return pageInfo;
    }

    @Override
    public List<Article> findAllHotlist() {
        List<Article> data = findHotlist();
        return data;
    }

    @Override
    public Article find(Integer wid) throws ArticleNotFoundExcption {
        Article data = findArticle(wid);
        if (data == null){
            throw new ArticleNotFoundExcption("您查询的数据不存在！");
        }
        return data;
    }

    @Override
    public void update(Article article) {
        //根据user.getId()查询用户数据
        Article data = findArticle(article.getWid());
        //判断数据是否为null
        if (data == null) {
            //是：抛出：UserNotFoundException
            throw new UserNotFoundException("修改文章失败！您尝试访问的数据不存在");
        }
        System.err.println(article);
        //向参数对象中封装：
        // - -modified_user > data.getUsername()
        // - -modified_time > new Date()
        article.setModifiedTime(new Date());
        // 执行修改：
        updateArticle(article);
    }

    @Override
    public void updateHot(Integer wid) {
        Integer Hotlist = null;
        //根据user.getId()查询用户数据
        Article data = findArticle(wid);
        //判断数据是否为null
        if (data == null) {
            //是：抛出：UserNotFoundException
            throw new UserNotFoundException("修改文章失败！您尝试访问的数据不存在");
        }

        if(data.getHotlist() == 0){
            Hotlist = 1;
        }

        if(data.getHotlist() == 1){
            Hotlist = 0;
        }
        System.err.println(wid);
        System.err.println(Hotlist);
        // 执行修改：
        updateHotlist(wid,Hotlist);
    }

    @Override
    public void delete(Integer id) throws ArticleNotFoundExcption, DeleterException {
        // 根据id查询数据：findById(id)
        Article data = findArticle(id);
        // 检查数据是否为null
        if (data == null) {
            // 是：抛出AddressNotFoundException
            throw new CarouserlMapNotFoundException("删除数据失败！，尝试删除的数据不存在！");
        }

        // 执行删除
        deleteById(id);
    }

    /**
     * 插入文章
     * @param article 文章数据
     * @return 返回受影响的行数
     */
   private void insert(Article article){
       // 调用持久层方法
       Integer rows = articleMapper.insert(article);
       // 判断用户插入是否成功
       if (rows != 1) {
           throw new InsertException("新增文章数据时出现未知错误！");
       }
   };


    /**
     * 查询所有文章
     * @return 返回对应数据
     */
    private List<Article> findAllArticle(){
        //返回查询到的数据
        return  articleMapper.findAllArticle();
    };

    /**
     * 查询燕窝做法所有文章
     * @return 返回对应数据
     */
    private List<Article>  findAll(String tag){
        return articleMapper.findAll(tag);
    };

    /**
     * 查询热榜文章
     * @return
     */
    private List<Article>  findHotlist(){
        return articleMapper.findHotlist();
    };

    /**
     * 根据文章wid 查询数据
     * @param wid 文章id
     * @return 返回对应的数据
     */
   private Article findArticle(Integer wid){
       //返回查询到的数据
       return  articleMapper.findArticle(wid);
   };


    /**
     * 根据文章wid 修改文章
     * @param article
     * @return
     */
    private void updateArticle(Article article){
        //执行更新，获取返回值
        Integer rows = articleMapper.updateArticle(article);
        //判断返回值，出错则抛出“更新时的未知错误异常”
        if (rows != 1) {
            throw new UpdateException("更新文章数据时出现未知错误！！！");
        }
    };



    /**
     * 根据文章wid 修改文章是否上热榜
     * @param wid
     * @return
     */
    private void updateHotlist(Integer wid,Integer Hotlist){
        //执行更新，获取返回值
        Integer rows = articleMapper.updateHotlist(wid,Hotlist);
        //判断返回值，出错则抛出“更新时的未知错误异常”
        if (rows != 1) {
            throw new UpdateException("更新文章数据时出现未知错误！！！");
        }
    };

    /**
     * 根据id删除图片
     * @param id
     * @return
     */
    private void deleteById(Integer id){
        Integer rows = articleMapper.deleteById(id);
        if (rows != 1) {
            throw new DeleterException("删除图片时出现未知错误！");
        }
    };

}
