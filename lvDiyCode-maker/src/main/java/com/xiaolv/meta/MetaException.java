package com.xiaolv.meta;

/**
 * @author 吕小匠
 * @MyHeart Endless
 */
public class MetaException extends RuntimeException{

    public MetaException(String message) {
        super(message);
    }

    public MetaException(String message, Throwable cause) {
        super(message, cause);
    }
}
