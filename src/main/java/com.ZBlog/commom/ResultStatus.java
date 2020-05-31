package com.ZBlog.commom;

public enum ResultStatus {
    SUCCESS(200, "success"),     //成功
    LOGINFAILED(302,"login failed"), //登录失败
    ACCOUNTBAN(303,"account ban"), //账户封禁
    NOTDATA(404,"no data"),//无数据
    SERVERERR(4500,"internal error"),//内部错误
    UNKNOWERR(4501,"unknown mistake"),//未知错误
    ILLEAGL(4502,"illegal"),//非法操作
    ROLEERR(4105,"User identity error"),//用户身份错误
    NOTOKEN(4107,"token invalid"),//Token验证失败
    SERVICE_EXCEPTION(5000, "service exception");

    private final int value;

    private final String reasonPhrase;

    public int value() {
        return this.value;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    ResultStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
