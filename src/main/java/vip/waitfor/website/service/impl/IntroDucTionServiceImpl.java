package vip.waitfor.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.waitfor.website.entity.IntroDucTion;
import vip.waitfor.website.mapper.IntroDucTionMapper;
import vip.waitfor.website.service.IIntroDucTionService;
import vip.waitfor.website.service.exception.DuplicatekeyException;
import vip.waitfor.website.service.exception.InsertException;
import vip.waitfor.website.service.exception.UpdateException;
import vip.waitfor.website.service.exception.UserNotFoundException;

import java.util.Date;
@Service
public class IntroDucTionServiceImpl implements IIntroDucTionService {

    @Autowired(required = false)
    private IntroDucTionMapper introDucTionMapper;

    @Override
    public IntroDucTion addNewArticle(IntroDucTion introDucTion,String username) throws DuplicatekeyException, InsertException {
        //四项日志
        Date now = new Date();
        introDucTion.setCreatedUser(username);
        introDucTion.setCreatedTime(now);
        introDucTion.setModifiedUser(username);
        introDucTion.setModifiedTime(now);
        //执行插入
        insert(introDucTion);
        return introDucTion;
    }

    @Override
    public IntroDucTion findintroDucTion(Integer id) {
        return find(id);
    }

    @Override
    public void update(IntroDucTion introDucTion) {
        //根据user.getId()查询用户数据
        IntroDucTion data = find(introDucTion.getId());
        //判断数据是否为null
        if (data == null) {
            //是：抛出：UserNotFoundException
            throw new UserNotFoundException("修改失败！您尝试访问的数据不存在");
        }
        //向参数对象中封装：
        // - -modified_user > data.getUsername()
        // - -modified_time > new Date()
        introDucTion.setModifiedTime(new Date());
        // 执行修改：
        updateArticle(introDucTion);
    }



    /**
     * 插入官网简介
     * @param introDucTion
     * @return
     */
   private void insert(IntroDucTion introDucTion){
       Integer rows = introDucTionMapper.insert(introDucTion);
       if (rows != 1){
           throw new InsertException("新增数据时出现未知错误！");
       }
   };

    /**
     * 查询官网简介
     * @return
     */
   private IntroDucTion find(Integer id){
       return introDucTionMapper.find(id);
   };

    /**
     * 修改id文章
     * @param introDucTion
     * @return
     */
   private void updateArticle(IntroDucTion introDucTion){
       Integer rows = introDucTionMapper.updateArticle(introDucTion);
       if (rows !=1){
           throw new UpdateException("修改数据出现位置错误");
       }
   };
}
