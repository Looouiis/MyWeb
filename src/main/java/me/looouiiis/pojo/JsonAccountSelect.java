package me.looouiiis.pojo;

import com.alibaba.fastjson2.JSON;

public class JsonAccountSelect {
    private boolean status;
    private String description;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getContext() {
        return context;
    }

    public void setContext(Object context) {
        this.context = context;
    }

    private Object context;
}
