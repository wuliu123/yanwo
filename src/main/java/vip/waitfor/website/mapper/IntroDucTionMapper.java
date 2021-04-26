package vip.waitfor.website.mapper;

import vip.waitfor.website.entity.Article;
import vip.waitfor.website.entity.IntroDucTion;

/**
 * 处理官网简介持久层
 * @author 刘先生
 */
public interface IntroDucTionMapper {

    /**
     * 插入官网简介
     * @param introDucTion
     * @return
     */
    Integer insert(IntroDucTion introDucTion);

    /**
     * 查询官网简介
     * @return
     */
    IntroDucTion find(Integer id);

    /**
     * 修改id文章
     * @param introDucTion
     * @return
     */
    Integer updateArticle(IntroDucTion introDucTion);
}
