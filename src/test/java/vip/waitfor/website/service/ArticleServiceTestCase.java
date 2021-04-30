package vip.waitfor.website.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vip.waitfor.website.entity.Article;
import vip.waitfor.website.mapper.ArticleMapper;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceTestCase {

    @Autowired(required = false)
    private IArticleService iArticleService;

    /**
     * 修改是否上热榜
     */
    @Test
    public void update() {
        iArticleService.updateHot(54);
        System.err.println("运行了");
    }
}
