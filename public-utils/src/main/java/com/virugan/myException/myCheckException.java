package com.virugan.myException;

public class myCheckException {

    public static Exception isNullException(String paramNme){
        return new RuntimeException(paramNme+" is Not allowed null !!!");
    }

    public static Exception isNotExistException(String paramNme){
        return new RuntimeException(paramNme+" is Not Exist !!!");
    }
}