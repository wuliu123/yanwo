package vip.waitfor.website.service;

import vip.waitfor.website.entity.Article;
import vip.waitfor.website.entity.IntroDucTion;
import vip.waitfor.website.service.exception.DuplicatekeyException;
import vip.waitfor.website.service.exception.InsertException;

/**
 * 处理官网简介业务层接口
 * @author 刘先生
 * @date 2021/4/9
 */
public interface IIntroDucTionService {

    /**
     * 插入简介
     * @param introDucTion 简介数据
     * @return
     * @throws DuplicatekeyException 像数据库插入数据异常
     * @throws InsertException  插入简介数据异常
     */
    IntroDucTion addNewArticle(IntroDucTion introDucTion,String username) throws DuplicatekeyException, InsertException;

    /**
     * 查询简介
     * @return
     */
    IntroDucTion findintroDucTion(Integer id);


    /**
     * 根据id 修改简介
     * @param introDucTion
     * @return
     */
    void update(IntroDucTion introDucTion);


}
