package com.movie.movie.controller.util;

import lombok.Data;

/**
 * 顶层类
 */
@Data
public class R {
    private Boolean flag;
    private Object data;
    private  String msg;

    public R(){

    }
    public R(Boolean flag){
        this.flag=flag;
    }

    public R(Boolean flag,Object data, String msg){
        this.flag=flag;
        this.data=data;
        this.msg=msg;
    }

    public R(Boolean flag,Object data){
        this.flag=flag;
        this.data=data;
    }

    public R(String msg){
        this.flag=false;
        this.msg=msg;
    }


}
