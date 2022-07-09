package com.sellers.manager.userinterface.exception;

import br.com.bradescoseguros.opin.application.dto.exception.BaseException;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class DataBaseCreateException extends RuntimeException implements Serializable {

    private final List<BaseException>  objDataBase;

    public DataBaseCreateException(String msg) {
        super(msg);
        objDataBase = null;
    }

    public DataBaseCreateException(String msg, List<BaseException> objDataBase) {
        super(msg);
        this.objDataBase = objDataBase;
    }

}
