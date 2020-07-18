package com.ZBlog.bean;

import java.security.Principal;

public class SocketUser implements Principal {
    String name;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
