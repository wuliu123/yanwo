package vip.waitfor.website.mapper;

import org.apache.ibatis.annotations.Param;
import vip.waitfor.website.entity.Article;
import vip.waitfor.website.entity.CarouselMap;

import java.util.Date;
import java.util.List;

/**
 *处理官网轮播图持久层
 * @author 刘先生
 */
public interface CarouselMapMapper {


    /**
     * 插入官网轮播图片
     * @param carouselMap 图片数据
     * @return 返回受影响的行数
     */
    Integer insert(CarouselMap carouselMap);

    /**
     * 查询所有图片
     * @return
     */
    List<CarouselMap> findAll();


    /**
     * 根据id删除图片
     * @param id
     * @return
     */
    Integer deleteById(Integer id);

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    CarouselMap findcarouselMap(Integer id);



}
