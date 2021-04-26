package vip.waitfor.website.service;


import vip.waitfor.website.entity.CarouselMap;
import vip.waitfor.website.service.exception.*;

import java.util.List;

/**
 * 处理轮播图数据业务层接口
 * @author 刘先生
 * @date 2021/4/7
 */
public interface ICarouselMapService {


    /**
     * 插入轮播图
     * @param url 图片地址
     * @return
     * @throws DuplicatekeyException 像数据库插入数据异常
     * @throws InsertException  插入图片数据异常
     */
    CarouselMap addcarouselMap(String url, String userName) throws DuplicatekeyException, InsertException;


    /**
     * 查询所有轮播图
     * @return 返回对应数据
     */
    List<CarouselMap> findAllCaroueslMap();


    /**
     * 根据id删除数据
     *
     * @param id id
     * @return 返回受影响行数
     */
    void delete(Integer id) throws CarouserlMapNotFoundException,DeleterException;

}
