package com.tenet.iot.util;

import java.io.Serializable;


public class Render implements Serializable {
    private String message;
    private boolean success;
    private Object result;

    private Render(boolean success, String message, Object result) {
        this.message = message;
        this.success = success;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getResult() {
        return result;
    }

    public static Render success() {
        return success(null);
    }

    public static Render success(Object object) {
        return new Render(true, null, object);
    }

    public static Render fail(String message) {
        return fail(message, null);
    }

    public static Render fail(String message, Object object) {
        return new Render(false, message, object);
    }
}
