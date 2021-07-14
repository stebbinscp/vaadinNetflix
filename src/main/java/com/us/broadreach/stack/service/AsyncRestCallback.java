package com.us.broadreach.stack.service;

@FunctionalInterface
public interface AsyncRestCallback<T> {
    void operationFinished(T results);
}
