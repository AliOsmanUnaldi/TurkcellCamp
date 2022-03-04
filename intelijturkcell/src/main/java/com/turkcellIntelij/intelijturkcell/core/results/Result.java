package com.turkcellIntelij.intelijturkcell.core.results;

public class Result {
    private boolean success;
    private String message;

    public Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success, String message) {
        this(success);
        this.message = message;
    }

    public boolean isSusccess() {
        return this.success;
    }

    public String getMesssage() {
        return this.message;
    }
}
