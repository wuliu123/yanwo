package vip.waitfor.website.entity;

import java.util.Date;

/**
 *官网简介实体类
 * @author 刘先生
 * @date 2021/4/23
 */
public class IntroDucTion  extends BaseEntity{
    private static final long serialVersionUID = 6471373545524502007L;
    private Integer id;
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "IntroDucTion{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
