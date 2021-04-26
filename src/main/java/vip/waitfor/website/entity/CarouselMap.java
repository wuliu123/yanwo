package vip.waitfor.website.entity;

/**
 * 官网轮播图实体类
 * @author 刘先生
 * @date 2021/4/14
 */
public class CarouselMap extends BaseEntity {
    private static final long serialVersionUID = 1337769836491472430L;
    private Integer id;
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "CarouselMap{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}
