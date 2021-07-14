package com.us.broadreach.stack.service;

@FunctionalInterface
public interface ResponseCallback<T> {
    void operationFinished(T results);
}
