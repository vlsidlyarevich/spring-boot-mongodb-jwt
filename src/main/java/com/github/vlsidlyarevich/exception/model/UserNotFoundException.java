package com.github.vlsidlyarevich.exception.model;


public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 2967357473314163159L;

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(final String message) {
        super(message);
    }

    public UserNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
