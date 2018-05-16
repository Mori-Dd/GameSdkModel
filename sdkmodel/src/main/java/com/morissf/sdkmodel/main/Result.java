package com.morissf.sdkmodel.main;

/**
 * Created by lgh on 2018/5/16.
 */
public enum Result {

    SUCCESS("登录成功"),
    CANCEL("用户取消"),
    ILLEGAL("账户验证失败"),
    NONESERVICE("无计费通道");

    public String msg;
    public int resultCode = 0;

    public Result setResultCode(int resultCode) {
        this.resultCode = resultCode;
        return this;
    }

    Result(String msg) {
        this.msg = msg;
    }
}
