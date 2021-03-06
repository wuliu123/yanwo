package vip.waitfor.website.util;

import java.io.Serializable;

/**
 * 服务端向客户端响应结果的类型
 * @param <E> 服务器向客户端响应的数据的类型
 * @date 2021/4/7
 */
public class ResponseResult<E> implements Serializable {
    private static final long serialVersionUID = 1569591439876361210L;
    private Integer state;
    private String message;
    private E data;

    public ResponseResult() {
        super();
    }

    public ResponseResult(Integer state) {
        super();
        setState(state);
    }

    public ResponseResult(Integer state, String message) {
        this(state);
        setMessage(message);
    }

    public ResponseResult(Integer state, Exception e) {
        this(state, e.getMessage());
    }

    public ResponseResult(Integer state, E data) {
        this(state);
        setData(data);
    }


    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "state=" + state +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
