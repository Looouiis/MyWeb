package me.looouiiis.exception;

public class SystemException extends RuntimeException { //项目运行过程中可预计且无法避免的异常
    //    final String ADD_ERR = "增加操作时出错";
//    final String DEL_ERR = "删除操作时出错";
//    final String UPD_ERR = "修改操作时出错";
//    final String SEL_ERR = "查询操作时出错";
    private Integer code;

    public SystemException(Integer code) {
        this.code = code;
    }

    public SystemException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public SystemException(Integer code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public SystemException(Integer code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
