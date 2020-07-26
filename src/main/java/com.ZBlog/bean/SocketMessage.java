package com.ZBlog.bean;

public class SocketMessage {
    String message;

    String fromUserName;

    String fromUserAvatar;

    String toUserName;

    public String getFromUserAvatar() {
        return fromUserAvatar;
    }

    public void setFromUserAvatar(String fromUserAvatar) {
        this.fromUserAvatar = fromUserAvatar;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    @Override
    public String toString() {
        return "SocketMessage{" +
                "message='" + message + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", fromUserAvatar='" + fromUserAvatar + '\'' +
                ", toUserName='" + toUserName + '\'' +
                '}';
    }
}
