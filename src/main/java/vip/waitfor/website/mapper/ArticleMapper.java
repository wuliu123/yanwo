package vip.waitfor.website.mapper;


import vip.waitfor.website.entity.Article;
import java.util.List;

/**
 * 处理文章数据持久层
 * @author 刘先生
 */
public interface ArticleMapper {
    /**
     * 插入文章
     * @param article 文章数据
     * @return 返回受影响的行数
     */
    Integer insert(Article article);

    /**
     * 查询所有文章
     * @return 返回对应数据
     */
    List<Article> findAllArticle();

    /**
     * 根据标签查询文章
     * @param tag 文章标签
     * @return 返回对应的数据
     */
    List<Article>  findAll(String tag);

    /**
     * 查询热榜文章
     * @return
     */
    List<Article>  findHotlist();

    /**
     * 根据文章wid 查询数据
     * @param wid 文章id
     * @return 返回对应的数据
     */
    Article findArticle(Integer wid);

    /**
     * 根据文章wid 修改文章
     * @param article
     * @return
     */
    Integer updateArticle(Article article);


    /**
     * 根据文章wid 修改文章是否上热榜
     * @param wid
     * @return
     */
    Integer updateHotlist(Integer wid,Integer Hotlist);

    /**
     * 根据id删除文章
     * @param id
     * @return
     */
    Integer deleteById(Integer id);

}
