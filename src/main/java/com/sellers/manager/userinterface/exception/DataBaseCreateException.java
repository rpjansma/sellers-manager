package com.sellers.manager.userinterface.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class DataBaseCreateException extends RuntimeException implements Serializable {

    private final List<Exception>  objDataBase;

    public DataBaseCreateException(String msg) {
        super(msg);
        objDataBase = null;
    }

    public DataBaseCreateException(String msg, List<Exception> objDataBase) {
        super(msg);
        this.objDataBase = objDataBase;
    }

}
