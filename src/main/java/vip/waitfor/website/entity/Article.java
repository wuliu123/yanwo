package vip.waitfor.website.entity;

import java.util.Date;

/**
 *  文章实体类
 * @author 刘先生
 * @date 2021/4/9
 */
public class Article extends BaseEntity{

    private static final long serialVersionUID = 3939828278401025236L;

    private Integer wid;
    private Integer uid;
    private String title;
    private String tag;
    private String introduction;
    private String conten;
    private String cover;
    private Integer isDelete;
    private Integer Hotlist;
    private Integer mun;
    private Integer commentID;

    public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getConten() {
        return conten;
    }

    public void setConten(String conten) {
        this.conten = conten;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getHotlist() {
        return Hotlist;
    }

    public void setHotlist(Integer hotlist) {
        Hotlist = hotlist;
    }

    public Integer getMun() {
        return mun;
    }

    public void setMun(Integer mun) {
        this.mun = mun;
    }

    public Integer getCommentID() {
        return commentID;
    }

    public void setCommentID(Integer commentID) {
        this.commentID = commentID;
    }

    @Override
    public String toString() {
        return "Article{" +
                "wid=" + wid +
                ", uid=" + uid +
                ", title='" + title + '\'' +
                ", tag='" + tag + '\'' +
                ", introduction='" + introduction + '\'' +
                ", conten='" + conten + '\'' +
                ", cover='" + cover + '\'' +
                ", isDelete=" + isDelete +
                ", Hotlist=" + Hotlist +
                ", mun=" + mun +
                ", commentID=" + commentID +
                '}';
    }
}
