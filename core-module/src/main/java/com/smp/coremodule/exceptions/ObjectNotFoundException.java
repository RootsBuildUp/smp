package com.smp.coremodule.exceptions;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String msg){
        super(msg);
    }
}
