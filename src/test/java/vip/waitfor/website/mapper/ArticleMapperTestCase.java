package vip.waitfor.website.mapper;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vip.waitfor.website.entity.Article;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleMapperTestCase {

    @Autowired(required = false)
    private ArticleMapper articleMapper;

    /**
     * 查询用户数据
     */
    @Test
    public void findByUsername() {
        List<Article> data = articleMapper.findAllArticle();
        System.err.println(data);
    }
}
