package vip.waitfor.website.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.waitfor.website.entity.Article;
import vip.waitfor.website.entity.CarouselMap;
import vip.waitfor.website.entity.User;
import vip.waitfor.website.mapper.CarouselMapMapper;
import vip.waitfor.website.service.ICarouselMapService;
import vip.waitfor.website.service.exception.*;

import java.util.Date;
import java.util.List;

/**
 * 处理轮播图数据的业务层实现类
 * @author 刘先生
 * @date 2021/4/14
 */
@Service
public class CarouselMapServiceImpl implements ICarouselMapService {

    @Autowired(required = false)
    private CarouselMapMapper carouselMapMapper;


    @Override
    public CarouselMap addcarouselMap(String url, String userName) throws DuplicatekeyException, InsertException {
        CarouselMap data = new CarouselMap();
        //补充非用户提交的数据
        //四项日志
        Date now = new Date();
        data.setCreatedUser(userName);
        data.setCreatedTime(now);
        data.setModifiedUser(userName);
        data.setModifiedTime(now);
        data.setUrl(url);
        //执行插入
        insert(data);
        return  data;
    }


    @Override
    public List<CarouselMap> findAllCaroueslMap() {
        return findAll();
    }

    @Override
    public void delete(Integer id) throws CarouserlMapNotFoundException,DeleterException {
        // 根据id查询数据：findById(id)
        CarouselMap data = findcarouselMap(id);
        // 检查数据是否为null
        if (data == null) {
            // 是：抛出AddressNotFoundException
            throw new CarouserlMapNotFoundException("删除数据失败！，尝试删除的数据不存在！");
        }

        // 执行删除
        deleteById(id);
    }


    /**
     * 插入官网轮播图片
     * @param carouselMap 图片地址
     * @return 返回受影响的行数
     */
    private void insert(CarouselMap carouselMap){
        // 调用持久层方法
        Integer rows = carouselMapMapper.insert(carouselMap);
        // 判断用户插入是否成功
        if (rows != 1) {
            throw new InsertException("新增图片数据时出现未知错误！");
        }
    };


    /**
     * 查询轮播图所有数据
     * @return
     */
    private List<CarouselMap> findAll(){
        return carouselMapMapper.findAll();
    };


    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    private CarouselMap findcarouselMap(Integer id){
        return carouselMapMapper.findcarouselMap(id);
    };


    /**
     * 根据id删除图片
     * @param id
     * @return
     */
    private void deleteById(Integer id){
        Integer rows = carouselMapMapper.deleteById(id);
        if (rows != 1) {
            throw new DeleterException("删除图片时出现未知错误！");
        }
    };

}
