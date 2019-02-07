package com.altran.ibanarriola.teamworktest.common;

public class DataWrapper<T> {
    private Throwable apiException;
    private T data;

    public DataWrapper(Throwable apiException) {
        this.apiException = apiException;
    }

    public DataWrapper(T data) {
        this.data = data;
    }

    public Throwable getApiException() {
        return apiException;
    }

    public T getData() {
        return data;
    }
}
