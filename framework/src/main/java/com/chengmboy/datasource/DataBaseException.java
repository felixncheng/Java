package com.chengmboy.datasource;

/**
 * @author cheng_mboy
 */
public class DataBaseException extends RuntimeException {

    public DataBaseException(String message) {
        super(message);
    }

    public DataBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
