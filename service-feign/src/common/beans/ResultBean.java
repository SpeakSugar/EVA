package beans;


import utils.json.JsonMapper;

import java.io.Serializable;

public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 3617154470380165248L;
    public static final int RETURN_SUCCESS = 1;
    public static final int RETURN_FAILURE = 0;
    public static final int CHECK_FAIL = 2;
    public static final int NO_PERMISSION = 3;
    public static final int USER_OPERATE_ERROR = 4;
    public static final int UNKNOWN_EXCEPTION = -99;

    /**
     * 返回的数据
     */
    private int returnCode = RETURN_SUCCESS;
    private String returnMsg = "success";
    private T content;

    public ResultBean(int returnCode, String returnMsg, T content) {
        super();
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
        this.content = content;
    }

    public ResultBean(int returnCode, String returnMsg) {
        super();
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }

    public ResultBean(T content) {
        super();
        this.content = content;
    }

    public ResultBean(Throwable e) {
        super();
        this.returnMsg = e.toString();
        this.returnCode = UNKNOWN_EXCEPTION;
    }

    public ResultBean() {
        super();
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        String json = JsonMapper.getDefault().toJson(this);
        return json;
    }
}
