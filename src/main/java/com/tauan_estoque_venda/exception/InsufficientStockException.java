package com.tauan_estoque_venda.exception;

public class InsufficientStockException extends RuntimeException{
    public InsufficientStockException(String msg){
        super(msg);
    }
}
