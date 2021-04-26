package vip.waitfor.website.service;

import vip.waitfor.website.entity.Article;
import vip.waitfor.website.service.exception.*;

import java.util.List;

/**
 * 处理文章数据业务层接口
 * @author 刘先生
 * @date 2021/4/9
 */
public interface IArticleService {

    /**
     * 插入文章
     * @param article 文章数据
     * @return
     * @throws DuplicatekeyException 像数据库插入数据异常
     * @throws InsertException  插入文章数据异常
     */
    Article addNewArticle(Article article,String userName,Integer uid) throws DuplicatekeyException, InsertException;

    /**
     * 查询所有文章
     * @return
     */
    Object findArticle(int page, int size);

    /**
     * 根据标签查询文章
     * @param tag 文章标签
     * @return 返回对应的数据
     */
    List<Article> findByAll(int page, int size,String tag);


    /**
     * 查询热榜文章
     * @return
     */
    List<Article>  findAllHotlist();


    /**
     * 根据wid查询文章数据
     * @param wid
     * @return 返回对应的数据
     */
    Article find(Integer wid) throws ArticleNotFoundExcption;


    /**
     * 根据文章wid 修改文章
     * @param article
     * @return
     */
    void update(Article article);


    /**
     * 根据id删除数据
     *
     * @param id id
     * @return 返回受影响行数
     */
    void delete(Integer id) throws ArticleNotFoundExcption, DeleterException;

}
