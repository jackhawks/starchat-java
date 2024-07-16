package com.starchat.entity;

public class BaseEntity {

    @SuppressWarnings("ConstantConditions")
    public boolean isNull() {
        return null == this;
    }

    @SuppressWarnings("ConstantConditions")
    public boolean isNotNull() {
        return null != this;
    }
}
