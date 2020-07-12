package com.ZBlog.bean;

public class SocketMessage {
    String message;
    /** 发送的目的地 */
    String destination;

    String fromUser;

    String toUser;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    @Override
    public String toString() {
        return "SocketMessage{" +
                "data='" + message + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
